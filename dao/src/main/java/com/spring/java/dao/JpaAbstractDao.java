package com.spring.java.dao;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.google.common.collect.Lists;
import com.spring.java.common.HasVersion;
import com.spring.java.dao.model.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public abstract class JpaAbstractDao<E extends BaseEntity<D>, D> implements Dao<D> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public D save(UUID id, D domain) {
        return save(id, domain, false);
    }

    private D save(UUID id, D domain, boolean flush) {
        E entity;
        try {
            entity = getEntityClass().getConstructor(domain.getClass()).newInstance(domain);
        } catch (Exception e) {
            log.error("Can't create entity for domain object {}", domain, e);
            throw new IllegalArgumentException("Can't create entity for domain object {" + domain + "}", e);
        }
        log.debug("Saving entity {}", entity);
        boolean isNew = entity.getUuid() == null;
        if (isNew) {
            entity.setCreatedTime(System.currentTimeMillis());
        } else {
            if (entity.getCreatedTime() == 0) {
                if (entity.getUuid().version() == 1) {
                    entity.setCreatedTime(Uuids.unixTimestamp(entity.getUuid()));
                } else {
                    entity.setCreatedTime(System.currentTimeMillis());
                }
            }
        }
        try {
            entity = doSave(entity, isNew, flush);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DaoUtil.getData(entity);
    }

    protected E doSave(E entity, boolean isNew, boolean flush) {
        boolean flushed = false;
        EntityManager entityManager = getEntityManager();
        if (isNew) {
            if (entity instanceof HasVersion versionedEntity) {
                versionedEntity.setVersion(1L);
            }
            entityManager.persist(entity);
        } else {
            if (entity instanceof HasVersion versionedEntity) {
                if (versionedEntity.getVersion() == null) {
                    HasVersion existingEntity = entityManager.find(versionedEntity.getClass(), entity.getUuid());
                    if (existingEntity != null) {
                        /*
                         * manually resetting the version to latest to allow force overwrite of the entity
                         * */
                        versionedEntity.setVersion(existingEntity.getVersion());
                    } else {
                        return doSave(entity, true, flush);
                    }
                }
                versionedEntity = entityManager.merge(versionedEntity);
                /*
                 * by default, Hibernate doesn't issue an update query and thus version increment
                 * if the entity was not modified. to bypass this and always increment the version, we do it manually
                 * */
                versionedEntity.setVersion(versionedEntity.getVersion() + 1);
                /*
                 * flushing and then removing the entity from the persistence context so that it is not affected
                 * by next flushes (e.g. when a transaction is committed) to avoid double version increment
                 * */
                entityManager.flush();
                entityManager.detach(versionedEntity);
                flushed = true;
                entity = (E) versionedEntity;
            } else {
                entity = entityManager.merge(entity);
            }
        }
        if (flush && !flushed) {
            entityManager.flush();
        }
        return entity;
    }


    @Transactional
    @Override
    public D saveAndFlush(UUID id, D domain) {
        return save(id, domain, true);
    }

    @Override
    public D findById(UUID id) {
        log.debug("Get entity by key {}",id);
        Optional<E> entity = getRepository().findById(id);
        return DaoUtil.getData(entity);
    }

    @Override
    public boolean existsById(UUID id) {
        log.debug("Exists by id {}", id);
        return getRepository().existsById(id);
    }

    @Transactional
    @Override
    public void removeById(UUID id) {
        JpaRepository<E, UUID> repository = getRepository();
        repository.deleteById(id);
        repository.flush();
        log.debug("Remove request: {}", id);
    }

    @Transactional
    @Override
    public void removeAllByIds(Collection<UUID> ids) {
        JpaRepository<E, UUID> repository = getRepository();
        ids.forEach(repository::deleteById);
        repository.flush();
    }

    @Override
    public List<D> find(UUID id) {
        List<E> entities = Lists.newArrayList(getRepository().findAll());
        return DaoUtil.convertDataList(entities);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected abstract Class<E> getEntityClass();

    protected abstract JpaRepository<E, UUID> getRepository();

}

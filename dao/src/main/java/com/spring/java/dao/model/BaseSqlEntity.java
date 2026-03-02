package com.spring.java.dao.model;


import com.spring.java.common.BaseData;
import com.spring.java.common.id.UUIDBased;
import com.spring.java.dao.DaoUtil;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
import java.util.function.Function;

@Data
@MappedSuperclass
public abstract class BaseSqlEntity<D> implements BaseEntity<D> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = ModelConstants.ID_PROPERTY, updatable = false, nullable = false)
    protected UUID id;

    @Column(name = ModelConstants.CREATED_TIME_PROPERTY, updatable = false)
    protected long createdTime;

    public BaseSqlEntity() {
    }

    public BaseSqlEntity(BaseData<?> domain) {
        this.id = domain.getUuidId();
        this.createdTime = domain.getCreatedTime();
    }

    public BaseSqlEntity(BaseSqlEntity<?> entity) {
        this.id = entity.id;
        this.createdTime = entity.createdTime;
    }

    @Override
    public UUID getUuid() {
        return id;
    }

    @Override
    public void setUuid(UUID id) {
        this.id = id;
    }

    @Override
    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        if (createdTime > 0) {
            this.createdTime = createdTime;
        }
    }

    protected static UUID getUuid(UUIDBased uuidBased) {
        if (uuidBased != null) {
            return uuidBased.getId();
        } else {
            return null;
        }
    }

    protected static <I> I getEntityId(UUID uuid, Function<UUID, I> creator) {
        return DaoUtil.toEntityId(uuid, creator);
    }

}

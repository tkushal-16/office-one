package com.spring.java.dao;


import com.google.common.util.concurrent.ListenableFuture;
import com.spring.java.common.EntityType;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface Dao<T> {

    List<T> find(UUID id);

    T findById(UUID id);

    ListenableFuture<T> findByIdAsync(UUID id);

    boolean existsById(UUID id);

    ListenableFuture<Boolean> existsByIdAsync(UUID id);

    T save(UUID id, T t);

    T saveAndFlush(UUID id, T t);

    void removeById(UUID id);

    void removeAllByIds(Collection<UUID> ids);

    List<UUID> findIdsByUUIDAndIdOffset(UUID id, UUID idOffset, int limit);

    default EntityType getEntityType() { return null; }


}

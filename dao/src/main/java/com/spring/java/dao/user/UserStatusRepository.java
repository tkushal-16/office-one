package com.spring.java.dao.user;

import com.spring.java.dao.model.sql.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatusEntity, Long> {
}

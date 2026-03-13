package com.spring.java.dao.user;

import com.spring.java.dao.model.sql.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatusEntity, Long> {
}

package com.spring.java.dao.leave;


import com.spring.java.common.user.User;
import com.spring.java.dao.model.sql.UserLeavesRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLeavesRecordRepository
        extends JpaRepository<UserLeavesRecordEntity, Long> {

    Optional<UserLeavesRecordEntity> findByUserAndYear(User user, Integer year);
}
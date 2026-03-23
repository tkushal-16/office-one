package com.spring.java.dao.leave;

import com.spring.java.common.user.User;
import com.spring.java.dao.model.sql.UserEntity;
import com.spring.java.dao.model.sql.UserLeavesRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLeavesRecordRepository
        extends JpaRepository<UserLeavesRecordEntity, Long> {

    Optional<UserLeavesRecordEntity> findByUserAndYear(UserEntity user, Integer year);
}
package com.spring.java.dao.user;

import com.spring.java.common.user.User;
import com.spring.java.dao.model.sql.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);


    List<User> findBySupervisor(User supervisor);
}

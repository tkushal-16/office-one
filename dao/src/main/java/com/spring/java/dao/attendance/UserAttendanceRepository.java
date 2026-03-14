package com.spring.java.dao.attendance;

import com.spring.java.common.user.User;
import com.spring.java.dao.model.sql.UserAttendanceEntity;
import com.spring.java.dao.model.sql.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserAttendanceRepository extends JpaRepository<UserAttendanceEntity, Long> {

    Optional<UserAttendanceEntity> findByUserAndDate(UserEntity user, LocalDate date);

    List<UserAttendanceEntity> findByUserOrderByDateDesc(User user);
}
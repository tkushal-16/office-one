package com.spring.java.dao.attendance;

import com.spring.java.dao.model.sql.UserAttendanceEntity;
import com.spring.java.dao.model.sql.UserInactiveTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInactiveTimeRepository extends JpaRepository<UserInactiveTimeEntity, Long> {

    List<UserInactiveTimeEntity> findByAttendance(UserAttendanceEntity attendance);
}
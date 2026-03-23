package com.spring.java.dao.attendance;

import com.spring.java.dao.model.sql.UserAttendanceEntity;
import com.spring.java.dao.model.sql.UserInactiveTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInactiveTimeRepository extends JpaRepository<UserInactiveTimeEntity, Long> {

    List<UserInactiveTimeEntity> findByAttendance(UserAttendanceEntity attendance);
}
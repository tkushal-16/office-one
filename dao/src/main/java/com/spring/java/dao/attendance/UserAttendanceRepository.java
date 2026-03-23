package com.spring.java.dao.attendance;

import com.spring.java.common.user.User;
import com.spring.java.dao.model.sql.UserAttendanceEntity;
import com.spring.java.dao.model.sql.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAttendanceRepository extends JpaRepository<UserAttendanceEntity, Long> {

    Optional<UserAttendanceEntity> findByUserAndDate(UserEntity user, LocalDate date);

    List<UserAttendanceEntity> findByUserOrderByDateDesc(User user);

    @Query("""
    SELECT DISTINCT a
    FROM UserAttendance a
    LEFT JOIN FETCH a.inactivePeriods ip
    JOIN FETCH a.user u
    WHERE u.id IN :userIds
    AND a.date = :targetDate
    """)
    List<UserAttendanceEntity> findAttendanceForUsers(
            List<Long> userIds,
            LocalDate targetDate
    );
}
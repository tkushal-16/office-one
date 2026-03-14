package com.spring.java.dao.leave;


import com.spring.java.common.user.User;
import com.spring.java.dao.model.sql.UserLeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserLeaveRequestRepository
        extends JpaRepository<UserLeaveRequestEntity, Long> {

    List<UserLeaveRequestEntity> findByUser(User user);

    List<UserLeaveRequestEntity> findByUserOrderByAppliedDateDesc(User user);

    @Query("""
           SELECT l FROM UserLeaveRequest l
           WHERE l.user = :user
           AND l.leaveStatus = 'APPROVED'
           AND l.startDate <= :targetDate
           AND l.endDate >= :targetDate
           """)
    Optional<UserLeaveRequestEntity> findApprovedLeaveForDate(User user, LocalDate targetDate);

    @Query("""
        SELECT l
        FROM UserLeaveRequest l
        WHERE l.user.id IN :userIds
        AND l.leaveStatus = 'APPROVED'
        AND l.startDate <= :targetDate
        AND l.endDate >= :targetDate
        """)
    List<UserLeaveRequestEntity> findApprovedLeaves(
            List<Long> userIds,
            LocalDate targetDate
    );
}
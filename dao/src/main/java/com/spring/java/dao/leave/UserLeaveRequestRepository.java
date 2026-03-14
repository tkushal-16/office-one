package com.spring.java.dao.leave;


import com.spring.java.common.user.User;
import com.spring.java.dao.model.sql.UserLeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLeaveRequestRepository
        extends JpaRepository<UserLeaveRequestEntity, Long> {

    List<UserLeaveRequestEntity> findByUser(User user);

    List<UserLeaveRequestEntity> findByUserOrderByAppliedDateDesc(User user);
}
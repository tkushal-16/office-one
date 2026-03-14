package com.spring.java.server.dto.leave;

import com.spring.java.common.leave.LeaveDayType;
import com.spring.java.common.leave.LeaveStatus;
import com.spring.java.common.leave.LeaveType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserLeaveResponseDTO {

    private Long id;

    private Long userId;

    private LeaveType leaveType;

    private String leaveReason;

    private LocalDate startDate;

    private LeaveDayType startDayType;

    private LocalDate endDate;

    private LeaveDayType endDayType;

    private LeaveStatus leaveStatus;

    private LocalDateTime appliedDate;

    private LocalDateTime updatedDate;
}
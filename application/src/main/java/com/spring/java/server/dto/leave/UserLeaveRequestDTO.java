package com.spring.java.server.dto.leave;

import com.spring.java.common.leave.LeaveDayType;
import com.spring.java.common.leave.LeaveType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserLeaveRequestDTO {

    @NotNull
    private LeaveType leaveType;

    @NotBlank
    private String leaveReason;

    @NotNull
    private LocalDate startDate;

    private LeaveDayType startDayType;

    @NotNull
    private LocalDate endDate;

    private LeaveDayType endDayType;
}
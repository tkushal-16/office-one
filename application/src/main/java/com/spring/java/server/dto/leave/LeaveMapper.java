package com.spring.java.server.dto.leave;

import com.spring.java.dao.model.sql.UserLeaveRequestEntity;

public class LeaveMapper {

    public static UserLeaveResponseDTO toDTO(UserLeaveRequestEntity leave) {

        return UserLeaveResponseDTO.builder()
                .id(leave.getId())
                .userId(leave.getUser().getId())
                .leaveType(leave.getLeaveType())
                .leaveReason(leave.getLeaveReason())
                .startDate(leave.getStartDate())
                .startDayType(leave.getStartDayType())
                .endDate(leave.getEndDate())
                .endDayType(leave.getEndDayType())
                .leaveStatus(leave.getLeaveStatus())
                .appliedDate(leave.getAppliedDate())
                .updatedDate(leave.getUpdatedDate())
                .build();
    }
}
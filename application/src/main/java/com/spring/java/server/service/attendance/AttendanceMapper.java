package com.spring.java.server.service.attendance;

import com.spring.java.dao.model.sql.UserAttendanceEntity;
import com.spring.java.dao.model.sql.UserInactiveTimeEntity;
import com.spring.java.server.dto.attendance.UserAttendanceResponseDTO;
import com.spring.java.server.dto.attendance.UserInactiveTimeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AttendanceMapper {

    private final AttendanceCalculationService calculationService;

    public UserAttendanceResponseDTO toDTO(UserAttendanceEntity attendance) {

        return UserAttendanceResponseDTO.builder()
                .id(attendance.getId())
                .user(attendance.getUser().getId())
                .date(attendance.getDate())
                .day(attendance.getDate().getDayOfWeek().toString())

                .checkIn(calculationService.formatLocalTime(attendance.getCheckIn()))
                .checkOut(calculationService.formatLocalTime(attendance.getCheckOut()))

                .totalDuration(calculationService.getTotalDuration(attendance))
                .workingHours(calculationService.getWorkingHours(attendance))
                .overtime(calculationService.getOvertime(attendance))

                .inactivePeriods(
                        attendance.getInactivePeriods()
                                .stream()
                                .map(this::toInactiveDTO)
                                .collect(Collectors.toList())
                )

                .build();
    }

    private UserInactiveTimeDTO toInactiveDTO(UserInactiveTimeEntity inactive) {

        return UserInactiveTimeDTO.builder()
                .id(inactive.getId())
                .pauseTime(calculationService.formatLocalTime(inactive.getPauseTime()))
                .resumeTime(calculationService.formatLocalTime(inactive.getResumeTime()))
                .build();
    }
}
package com.spring.java.server.dto.attendance;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAttendanceResponseDTO {

    private Long id;
    private Long user;
    private LocalDate date;
    private String day;

    private String checkIn;
    private String checkOut;

    private String totalDuration;
    private String workingHours;
    private String overtime;

    private List<UserInactiveTimeDTO> inactivePeriods;

    private String statusDisplay;
    private String supervisorName;
}
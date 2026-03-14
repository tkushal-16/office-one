package com.spring.java.server.service.attendance;

import com.spring.java.dao.model.sql.UserAttendanceEntity;
import com.spring.java.dao.model.sql.UserInactiveTimeEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AttendanceCalculationService {

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("hh:mm a");

    public String formatDuration(Duration duration) {

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return hours + "h " + minutes + "m";
    }

    public String getTotalDuration(UserAttendanceEntity attendance) {

        if (attendance.getCheckIn() != null && attendance.getCheckOut() != null) {

            Duration duration = Duration.between(
                    attendance.getCheckIn(),
                    attendance.getCheckOut()
            );

            return formatDuration(duration);
        }

        return "0h 0m";
    }

    public String getWorkingHours(UserAttendanceEntity attendance) {

        if (attendance.getCheckIn() == null || attendance.getCheckOut() == null) {
            return "0h 0m";
        }

        Duration totalWork = Duration.between(
                attendance.getCheckIn(),
                attendance.getCheckOut()
        );

        // subtract lunch break
        totalWork = totalWork.minusHours(1);

        Duration inactiveTotal = Duration.ZERO;

        for (UserInactiveTimeEntity period : attendance.getInactivePeriods()) {

            if (period.getResumeTime() != null) {

                Duration inactive = Duration.between(
                        period.getPauseTime(),
                        period.getResumeTime()
                );

                inactiveTotal = inactiveTotal.plus(inactive);
            }
        }

        totalWork = totalWork.minus(inactiveTotal);

        if (totalWork.isNegative()) {
            totalWork = Duration.ZERO;
        }

        return formatDuration(totalWork);
    }

    public String getOvertime(UserAttendanceEntity attendance) {

        if (attendance.getCheckIn() == null || attendance.getCheckOut() == null) {
            return "0h 0m";
        }

        Duration workingDuration = Duration.between(
                attendance.getCheckIn(),
                attendance.getCheckOut()
        );

        workingDuration = workingDuration.minusHours(1);

        Duration standard = Duration.ofHours(8);

        if (workingDuration.compareTo(standard) > 0) {

            Duration overtime = workingDuration.minus(standard);

            return formatDuration(overtime);
        }

        return "0h 0m";
    }

    public String formatLocalTime(LocalDateTime time) {

        if (time == null) return null;

        return time.format(TIME_FORMAT);
    }

}
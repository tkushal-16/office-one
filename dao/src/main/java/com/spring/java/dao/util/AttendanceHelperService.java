package com.spring.java.dao.util;

import com.spring.java.common.user.User;
import com.spring.java.dao.attendance.UserAttendanceRepository;
import com.spring.java.dao.leave.UserLeaveRequestRepository;
import com.spring.java.dao.model.sql.UserAttendanceEntity;
import com.spring.java.dao.model.sql.UserLeaveRequestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttendanceHelperService {

    private final UserAttendanceRepository attendanceRepository;
    private final UserLeaveRequestRepository leaveRequestRepository;

    /**
     * Convert duration into "Xh Ym"
     */
    public String formatDuration(Duration duration) {

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return hours + "h " + minutes + "m";
    }

    /**
     * Generate attendance summary for users for a specific date
     */
    public List<Map<String, Object>> getAttendanceSummaryForUsers(
            List<User> users,
            LocalDate targetDate,
            User defaultSupervisor
    ) {

        List<Map<String, Object>> response = new ArrayList<>();

//  TODO: This is incomplete implementation and need to be implemented
//        for (User user : users) {
//
//            Optional<UserAttendanceEntity> attendanceOpt =
//                    attendanceRepository.findByUserAndDate(user, targetDate);
//
//            String supervisorName = "N/A";
//
//            try {
//                if (user.getProfile() != null) {
//                    User supervisor = user.getProfile().getSupervisorId();
//
//                    if (supervisor == null) {
//                        supervisor = defaultSupervisor;
//                    }
//
//                    if (supervisor != null) {
//                        supervisorName = supervisor.getFullName();
//                    }
//                }
//            } catch (Exception ignored) {}
//
//            Map<String, Object> data = new HashMap<>();
//
//            if (attendanceOpt.isPresent()) {
//
//                UserAttendanceEntity attendance = attendanceOpt.get();
//
//                data.put("id", attendance.getId());
//                data.put("user", user.getId());
//                data.put("date", targetDate);
//                data.put("check_in", attendance.getCheckIn());
//                data.put("check_out", attendance.getCheckOut());
//                data.put("supervisor_name", supervisorName);
//
//            } else {
//
//                Optional<UserLeaveRequestEntity> leave =
//                        leaveRequestRepository
//                                .findApprovedLeaveForDate(user, targetDate);
//
//                String status = leave.isPresent() ? "On Leave" : "Absent";
//
//                data.put("id", null);
//                data.put("user", user.getId());
//                data.put("date", targetDate);
//                data.put("check_in", null);
//                data.put("check_out", null);
//                data.put("total_duration", "0h 0m");
//                data.put("working_hours", "0h 0m");
//                data.put("overtime", "0h 0m");
//                data.put("inactive_periods", new ArrayList<>());
//                data.put("status_display", status);
//                data.put("day", targetDate.getDayOfWeek().toString());
//                data.put("supervisor_name", supervisorName);
//            }
//
//            response.add(data);
//        }

        return response;
    }
}
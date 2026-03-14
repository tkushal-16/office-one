package com.spring.java.server.service.attendance;

import com.spring.java.common.user.User;
import com.spring.java.dao.attendance.UserAttendanceRepository;
import com.spring.java.dao.attendance.UserInactiveTimeRepository;
import com.spring.java.dao.model.sql.UserAttendanceEntity;
import com.spring.java.dao.model.sql.UserEntity;
import com.spring.java.dao.model.sql.UserInactiveTimeEntity;
import com.spring.java.dao.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final UserRepository userRepository;
    private final UserAttendanceRepository attendanceRepository;
    private final UserInactiveTimeRepository inactiveRepository;

    /**
     * CHECK IN
     */
    public Map<String, Object> checkIn(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow();

        LocalDate today = LocalDate.now();

        UserAttendanceEntity attendance =
                attendanceRepository
                        .findByUserAndDate(user, today)
                        .orElseGet(() -> {

                            UserAttendanceEntity newAttendance =
                                    new UserAttendanceEntity();

                            newAttendance.setUser(user);
                            newAttendance.setDate(today);

                            return attendanceRepository.save(newAttendance);
                        });

        if (attendance.getCheckIn() != null) {

            return Map.of("message", "Already checked in.");
        }

        attendance.setCheckIn(LocalDateTime.now());

        attendanceRepository.save(attendance);

        return Map.of("message", "Check-in recorded.");
    }

    /**
     * CHECK OUT
     */
    public Map<String, Object> checkOut(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow();

        LocalDate today = LocalDate.now();

        UserAttendanceEntity attendance =
                attendanceRepository
                        .findByUserAndDate(user, today)
                        .orElseThrow();

        if (attendance.getCheckOut() != null) {

            return Map.of("message", "Already checked out.");
        }

        attendance.setCheckOut(LocalDateTime.now());

        attendanceRepository.save(attendance);

        return Map.of(
                "message", "Check-out recorded",
                "attendance_id", attendance.getId()
        );
    }

    /**
     * OUT OF OFFICE
     */
    public Map<String, String> outOfOffice(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow();

        LocalDate today = LocalDate.now();

        UserAttendanceEntity attendance =
                attendanceRepository
                        .findByUserAndDate(user, today)
                        .orElseThrow();

        boolean activeBreak =
                inactiveRepository
                        .findByAttendance(attendance)
                        .stream()
                        .anyMatch(p -> p.getResumeTime() == null);

        if (activeBreak) {

            return Map.of("message", "Already out of office.");
        }

        UserInactiveTimeEntity inactive = new UserInactiveTimeEntity();

        inactive.setAttendance(attendance);
        inactive.setPauseTime(LocalDateTime.now());

        inactiveRepository.save(inactive);

        return Map.of("message", "You are out of office now.");
    }

    /**
     * BACK IN OFFICE
     */
    public Map<String, String> backInOffice(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow();

        LocalDate today = LocalDate.now();

        UserAttendanceEntity attendance =
                attendanceRepository
                        .findByUserAndDate(user, today)
                        .orElseThrow();

        Optional<UserInactiveTimeEntity> inactive =
                inactiveRepository
                        .findByAttendance(attendance)
                        .stream()
                        .filter(i -> i.getResumeTime() == null)
                        .findFirst();

        if (inactive.isEmpty()) {

            return Map.of(
                    "message",
                    "No active out-of-office record found."
            );
        }

        UserInactiveTimeEntity inactiveTime = inactive.get();

        inactiveTime.setResumeTime(LocalDateTime.now());

        inactiveRepository.save(inactiveTime);

        return Map.of("message", "You are back in office now.");
    }

    public Map<String, Object> getWeekSummary(String email, String weekType) {

        UserEntity user = userRepository.findByEmail(email).orElseThrow();

        LocalDate today = LocalDate.now();

        LocalDate startOfWeek;

        if ("current".equalsIgnoreCase(weekType)) {

            startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);

        } else if ("past".equalsIgnoreCase(weekType)) {

            startOfWeek = today.minusDays(today.getDayOfWeek().getValue() + 6);

        } else {

            throw new RuntimeException("Invalid week type");
        }

        LocalDate endOfWeek = startOfWeek.plusDays(6);

        //TODO : This is yet to be implemented
//        List<UserAttendanceEntity> attendanceList =
//                attendanceRepository.findByUserAndDateBetween(
//                        user,
//                        startOfWeek,
//                        endOfWeek
//                );

        return Map.of(
                "week_type", weekType,
                "start_date", startOfWeek,
                "end_date", endOfWeek
                //"records", attendanceList.size()
        );
    }

}
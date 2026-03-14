package com.spring.java.server.controller;

import com.spring.java.server.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    /**
     * Check-in
     */
    @PostMapping("/check-in")
    public Map<String, Object> checkIn(Principal principal) {

        return attendanceService.checkIn(principal.getName());
    }

    /**
     * Check-out
     */
    @PostMapping("/check-out")
    public Map<String, Object> checkOut(Principal principal) {

        return attendanceService.checkOut(principal.getName());
    }

    /**
     * Out of office (pause work)
     */
    @PostMapping("/out-of-office")
    public Map<String, String> outOfOffice(Principal principal) {

        return attendanceService.outOfOffice(principal.getName());
    }

    /**
     * Back in office (resume work)
     */
    @PostMapping("/back-in-office")
    public Map<String, String> backInOffice(Principal principal) {

        return attendanceService.backInOffice(principal.getName());
    }

    //TODO : This is incomplete implementation
    /**
     * Admin attendance view
     */
    @GetMapping("/admin/attendance-view")
    public Object adminAttendance(
            @RequestParam(required = false) LocalDate date
    ) {

//        return attendanceService.getAdminAttendance(date);
        return new Object();
    }

    /**
     * Team lead attendance
     */
    @GetMapping("/teamlead/attendance-view")
    public Object teamLeadAttendance(
            Principal principal,
            @RequestParam(required = false) LocalDate date
    ) {

        //TODO : yet to be implemented
//        return attendanceService.getTeamLeadAttendance(
//                principal.getName(),
//                date
//        );
        return new Object();
    }

    /**
     * Weekly attendance summary
     */
    @GetMapping("/week-summary")
    public Object weekSummary(
            Principal principal,
            @RequestParam(defaultValue = "current") String week
    ) {

        return attendanceService.getWeekSummary(
                principal.getName(),
                week
        );
    }
}
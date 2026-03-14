package com.spring.java.server.controller;

import com.spring.java.common.user.User;
import com.spring.java.server.dto.leave.UserLeaveRequestDTO;
import com.spring.java.server.dto.leave.UserLeaveResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    // Apply Leave
    @PostMapping("/apply")
    public UserLeaveResponseDTO applyLeave(
            @AuthenticationPrincipal User user,
            @RequestBody UserLeaveRequestDTO requestDTO
    ) {
        return leaveService.applyLeave(user, requestDTO);
    }

    // Admin list of leave requests
    @GetMapping("/admin/paid-requests")
    public List<UserLeaveResponseDTO> getAllLeaves(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String leaveType,
            @RequestParam(required = false) Long userId
    ) {
        return leaveService.getAllLeaveRequests(status, leaveType, userId);
    }

    // Team lead sick leave requests
    @GetMapping("/team-lead/sick-requests")
    public List<UserLeaveResponseDTO> getTeamSickLeaves(
            @AuthenticationPrincipal User user
    ) {
        return leaveService.getTeamSickLeaves(user);
    }

    // Approve / Decline
    @PostMapping("/approve-decline/{id}")
    public String approveDeclineLeave(
            @AuthenticationPrincipal User approver,
            @PathVariable Long id,
            @RequestParam String action
    ) {
        return leaveService.processLeaveApproval(approver, id, action);
    }

    // Leave balance
    @GetMapping("/balance")
    public Object getOwnLeaveBalance(
            @AuthenticationPrincipal User user
    ) {
        return leaveService.getLeaveBalance(user, null);
    }

    @GetMapping("/balance/{userId}")
    public Object getUserLeaveBalance(
            @AuthenticationPrincipal User admin,
            @PathVariable Long userId
    ) {
        return leaveService.getLeaveBalance(admin, userId);
    }
}
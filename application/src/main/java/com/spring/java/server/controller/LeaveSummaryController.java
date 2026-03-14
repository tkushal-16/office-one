package com.spring.java.server.controller;



import com.spring.java.common.user.User;
import com.spring.java.server.service.leave.LeaveSummaryService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leave/summary")
@RequiredArgsConstructor
public class LeaveSummaryController {

    private final LeaveSummaryService leaveSummaryService;

    @GetMapping("/monthly-summary")
    public Object getOwnSummary(@AuthenticationPrincipal User user) {
        return leaveSummaryService.getMonthlySummary(user, null);
    }

    @GetMapping("/monthly-summary/{userId}")
    public Object getUserSummary(
            @AuthenticationPrincipal User admin,
            @PathVariable Long userId
    ) {
        return leaveSummaryService.getMonthlySummary(admin, userId);
    }
}
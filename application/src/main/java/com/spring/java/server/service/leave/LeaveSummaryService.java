package com.spring.java.server.service.leave;

import com.spring.java.common.leave.LeaveStatus;
import com.spring.java.common.user.User;
import com.spring.java.dao.leave.UserLeaveRequestRepository;
import com.spring.java.dao.model.sql.UserLeaveRequestEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LeaveSummaryService {

    private final UserLeaveRequestRepository leaveRepository;

    public Map<String,Object> getMonthlySummary(User user, Long userId) {

        int year = LocalDate.now().getYear();

        List<UserLeaveRequestEntity> leaves =
                leaveRepository.findAll()
                        .stream()
                        .filter(l -> l.getUser().equals(user))
                        .filter(l -> l.getLeaveStatus() == LeaveStatus.APPROVED)
                        .toList();

        Map<String,Double> monthly = new HashMap<>();

        for (UserLeaveRequestEntity leave : leaves) {

            String month = leave.getStartDate().getMonth().name();

            monthly.put(month,
                    monthly.getOrDefault(month,0.0) + 1);
        }

        Map<String,Object> response = new HashMap<>();

        response.put("user",user.getEmail());
        response.put("year",year);
        response.put("monthly_summary",monthly);

        return response;
    }
}
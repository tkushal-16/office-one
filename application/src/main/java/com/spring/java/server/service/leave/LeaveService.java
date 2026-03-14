package com.spring.java.server.service.leave;

import com.spring.java.common.leave.LeaveStatus;
import com.spring.java.common.leave.LeaveType;
import com.spring.java.common.user.User;
import com.spring.java.common.user.UserRole;
import com.spring.java.dao.leave.UserLeaveRequestRepository;
import com.spring.java.dao.leave.UserLeavesRecordRepository;
import com.spring.java.dao.model.sql.UserEntity;
import com.spring.java.dao.model.sql.UserLeaveRequestEntity;
import com.spring.java.dao.model.sql.UserLeavesRecordEntity;
import com.spring.java.dao.user.UserRepository;
import com.spring.java.dao.util.LeaveCalculationService;
import com.spring.java.dao.util.UserTeamService;
import com.spring.java.server.dto.leave.LeaveMapper;
import com.spring.java.server.dto.leave.UserLeaveRequestDTO;
import com.spring.java.server.dto.leave.UserLeaveResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final UserLeaveRequestRepository leaveRepository;
    private final UserLeavesRecordRepository leaveRecordRepository;
    private final LeaveCalculationService leaveCalculationService;
    private final UserRepository userRepository;
    private final UserTeamService teamService;

    // Apply Leave
    public UserLeaveResponseDTO applyLeave(User user, UserLeaveRequestDTO dto) {

        UserLeaveRequestEntity leave = UserLeaveRequestEntity.builder()
                .user(user)
                .leaveType(dto.getLeaveType())
                .leaveReason(dto.getLeaveReason())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .startDayType(dto.getStartDayType())
                .endDayType(dto.getEndDayType())
                .leaveStatus(LeaveStatus.PENDING)
                .build();

        leaveRepository.save(leave);

        return LeaveMapper.toDTO(leave);
    }

    // Admin list
    public List<UserLeaveResponseDTO> getAllLeaveRequests(
            String status,
            String leaveType,
            Long userId
    ) {

        List<UserLeaveRequestEntity> leaves = leaveRepository.findAll();

        return leaves.stream()
                .filter(l -> leaveType == null || l.getLeaveType().name().equalsIgnoreCase(leaveType))
                .filter(l -> userId == null || l.getUser().getId().equals(userId))
                .filter(l -> {
                    if (status == null) return true;
                    if (status.equalsIgnoreCase("pending"))
                        return l.getLeaveStatus() == LeaveStatus.PENDING;
                    if (status.equalsIgnoreCase("resolved"))
                        return l.getLeaveStatus() != LeaveStatus.PENDING;
                    return true;
                })
                .map(LeaveMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Team sick leaves
    public List<UserLeaveResponseDTO> getTeamSickLeaves(User user) {

        List<User> members = teamService.getTeamMembers(user);

        return leaveRepository.findAll()
                .stream()
                .filter(l -> members.contains(l.getUser()))
                .filter(l -> l.getLeaveType() == LeaveType.SICK)
                .map(LeaveMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Approve / Decline
    public String processLeaveApproval(User approver, Long leaveId, String action) {

        if (approver.getRole() != UserRole.ADMIN &&
            approver.getRole() != UserRole.TEAM_LEAD) {

            throw new RuntimeException("Permission denied");
        }

        UserLeaveRequestEntity leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        if (leave.getLeaveStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Leave already processed");
        }

        LeaveStatus newStatus = LeaveStatus.valueOf(action);

        leave.setLeaveStatus(newStatus);
        leaveRepository.save(leave);

        if (newStatus == LeaveStatus.APPROVED) {

            double leaveDays = leaveCalculationService.calculateTotalLeaveDays(
                    leave.getStartDate(),
                    leave.getEndDate(),
                    leave.getStartDayType(),
                    leave.getEndDayType()
            );

            int year = leave.getStartDate().getYear();

            UserLeavesRecordEntity record =
                    leaveRecordRepository.findByUserAndYear(leave.getUser(), year)
                            .orElseGet(() -> UserLeavesRecordEntity.builder()
                                    .user(leave.getUser())
                                    .year(year)
                                    .paidLeaves(12.0)
                                    .sickLeaves(5.0)
                                    .build());

            if (leave.getLeaveType() == LeaveType.SICK) {
                record.setSickLeaves(Math.max(0, record.getSickLeaves() - leaveDays));
            }

            if (leave.getLeaveType() == LeaveType.PAID) {
                record.setPaidLeaves(Math.max(0, record.getPaidLeaves() - leaveDays));
            }

            leaveRecordRepository.save(record);
        }

        return "Leave " + action.toLowerCase() + " successfully";
    }

    // Leave Balance
    public Object getLeaveBalance(User requester, Long userId) {

        UserEntity user;

        if (userId != null && requester.getRole() == UserRole.ADMIN) {
            user = userRepository.findById(userId)
                    .orElseThrow();
//        } else {
//            user = requester;
//        }

        int year = LocalDate.now().getYear();

        UserLeavesRecordEntity record =
                leaveRecordRepository.findByUserAndYear(user, year)
                        .orElseGet(() -> UserLeavesRecordEntity.builder()
                                .user(user)
                                .year(year)
                                .paidLeaves(12.0)
                                .sickLeaves(5.0)
                                .build());

        return record;
    }
}
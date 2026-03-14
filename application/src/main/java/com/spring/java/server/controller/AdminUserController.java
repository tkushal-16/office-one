package com.spring.java.server.controller;

import com.spring.java.common.user.User;
import com.spring.java.common.user.UserRole;
import com.spring.java.server.dto.user.AdminUserRegistrationRequest;
import com.spring.java.server.dto.user.UserResponseDto;
import com.spring.java.server.dto.user.UserStatusResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

//    private final UserRepository userRepository;
//    private final UserProfileRepository profileRepository;
//    private final UserStatusRepository statusRepository;
    private final PasswordEncoder passwordEncoder;

    // Equivalent to AdminUserRegistrationView
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerAdmin(
            @Valid @RequestBody AdminUserRegistrationRequest request) {

        if (!request.getPassword().equals(request.getRePassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = new User();
        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.valueOf(request.getRole()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDesignation(request.getDesignation());
        user.setTeam(request.getTeam());
        user.setActive(true);

        //TODO : This is to be implemented in the service layer
//        userRepository.save(user);

//        return ResponseEntity.ok(
//                UserResponse.builder()
//                        .id(user.getId())
//                        .email(user.getEmail())
//                        .role(user.getRole())
//                        .isActive(user.isActive())
//                        .firstName(user.getFirstName())
//                        .lastName(user.getLastName())
//                        .designation(user.getDesignation())
//                        .team(user.getTeam())
//                        .build()
//        );

        return ResponseEntity.ok(UserResponseDto.builder().build());
    }

    // Equivalent to AdminUserAccountListView
    @GetMapping("/user-accounts")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

//        List<UserResponseDto> users = userRepository.findAll()
//                .stream()
//                .map(user -> UserResponse.builder()
//                        .id(user.getId())
//                        .email(user.getEmail())
//                        .role(user.getRole())
//                        .isActive(user.isActive())
//                        .firstName(user.getFirstName())
//                        .lastName(user.getLastName())
//                        .designation(user.getDesignation())
//                        .team(user.getTeam())
//                        .build())
//                .collect(Collectors.toList());

        return ResponseEntity.ok(new ArrayList<>());
    }

    // Equivalent to AdminUserStatusesListView
    @GetMapping("/user-status")
    public ResponseEntity<List<UserStatusResponse>> getAllStatuses() {

//        List<UserStatusResponse> statuses = statusRepository.findAll()
//                .stream()
//                .map(status -> UserStatusResponse.builder()
//                        .id(status.getId())
//                        .userId(status.getUser().getId())
//                        .isOnline(status.isOnline())
//                        .lastLoginTime(status.getLastLoginTime())
//                        .lastLogoutTime(status.getLastLogoutTime())
//                        .build())
//                .collect(Collectors.toList());

        return ResponseEntity.ok(new ArrayList<>());
    }
}
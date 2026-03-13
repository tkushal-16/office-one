package com.spring.java.server.controller;

import com.spring.java.common.user.User;
import com.spring.java.common.user.UserStatus;
import com.spring.java.dao.user.UserProfileRepository;
import com.spring.java.dao.user.UserRepository;
import com.spring.java.dao.user.UserStatusRepository;
import com.spring.java.server.dto.UserResponseDto;
import com.spring.java.server.dto.UserStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;
    private final UserStatusRepository statusRepository;

    // Equivalent to UserAccountView
    @GetMapping("/account")
    public ResponseEntity<UserResponseDto> getAccount(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                UserResponseDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .role(String.valueOf(user.getRole()))
                        .isActive(user.isActive())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .designation(user.getDesignation())
                        .team(user.getTeam())
                        .build()
        );
    }

    // Equivalent to UserLogoutView
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @AuthenticationPrincipal User user) {

//        UserStatus status = statusRepository
//                .findByUser(user)
//                .orElse(new UserStatus());

//        status.setUser(user);
//        status.setOnline(false);
//        status.setLastLogoutTime(LocalDateTime.now());

        return ResponseEntity.ok("Successfully logged out.");
    }

    // Equivalent to UserStatusView
    @GetMapping("/status")
    public ResponseEntity<UserStatusResponse> getStatus(
            @AuthenticationPrincipal User user) {

        //TODO: This is service layer which is yet to be implemented

//        UserStatus status = statusRepository
//                .findByUser(user)
//                .orElseThrow(() -> new RuntimeException("Status not found"));
//
//        return ResponseEntity.ok(
//                UserStatusResponse.builder()
//                        .id(status.getId())
//                        .userId(user.getId())
//                        .isOnline(status.isOnline())
//                        .lastLoginTime(status.getLastLoginTime())
//                        .lastLogoutTime(status.getLastLogoutTime())
//                        .build()
//        );
        return ResponseEntity.ok(UserStatusResponse.builder().build());
    }
}

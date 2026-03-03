package com.spring.java.server.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserStatusResponse {

    private Long id;
    private Long userId;
    private boolean isOnline;
    private LocalDateTime lastLoginTime;
    private LocalDateTime lastLogoutTime;
}
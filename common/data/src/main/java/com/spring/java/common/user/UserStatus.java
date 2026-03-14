package com.spring.java.common.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserStatus implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;

    private LocalDateTime lastLoginTime;
    private LocalDateTime lastLogoutTime;

    private boolean online;

}
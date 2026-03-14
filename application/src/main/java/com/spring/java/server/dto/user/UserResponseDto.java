package com.spring.java.server.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private Long id;
    private String email;
    private String role;
    private boolean isActive;
    private String firstName;
    private String lastName;
    private String designation;
    private String team;
}
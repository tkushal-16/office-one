package com.spring.java.common.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;

    private boolean active;
    private boolean staff;
    private boolean superuser;

    private LocalDateTime dateJoined;
    private String designation;
    private String team;

    private UserProfile profile;
    private UserStatus status;

}
package com.spring.java.common.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserProfile implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private String phoneNumber;
    private String personalEmail;

    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;

    private Long supervisorId;

    private String panNo;
    private String aadharNo;
    private String address;

    private UserBankDetails bankDetails;

}
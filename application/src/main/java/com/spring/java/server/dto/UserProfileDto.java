package com.spring.java.server.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserProfileDto {

    private Long id;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String personalEmail;
    private String address;
    private LocalDate dateOfJoin;
    private Long supervisorId;
    private String aadharNo;
    private String panNo;

    private UserBankDetailsDto bankDetails;
}
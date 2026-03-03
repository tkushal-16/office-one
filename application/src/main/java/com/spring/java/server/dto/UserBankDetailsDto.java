package com.spring.java.server.dto;

import lombok.Data;

@Data
public class UserBankDetailsDto {

    private String bankAccountNo;
    private String bankName;
    private String ifscCode;
    private String accountType;
}
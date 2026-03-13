package com.spring.java.common.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserBankDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long profileId;

    private String bankAccountNo;
    private String bankName;
    private String ifscCode;
    private String accountType;

}
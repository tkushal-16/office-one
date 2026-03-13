package com.spring.java.dao.model.sql;

import com.spring.java.dao.util.AESAttributeConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_bank_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBankDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfileEntity profile;

    @Convert(converter = AESAttributeConverter.class)
    private String bankAccountNo;

    @Convert(converter = AESAttributeConverter.class)
    private String bankName;

    @Convert(converter = AESAttributeConverter.class)
    private String ifscCode;

    @Convert(converter = AESAttributeConverter.class)
    private String accountType;
}
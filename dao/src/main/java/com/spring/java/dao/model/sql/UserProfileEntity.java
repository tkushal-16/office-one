package com.spring.java.dao.model.sql;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String phoneNumber;

    private String personalEmail = "user@gmail.com";

    private LocalDate dateOfBirth;

    private LocalDate dateOfJoin;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private UserEntity supervisor;

    private String panNo;

    private String aadharNo;

    @Column(columnDefinition = "TEXT")
    private String address;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private UserBankDetailsEntity bankDetails;
}
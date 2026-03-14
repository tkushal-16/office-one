package com.spring.java.dao.model.sql;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "company_holidays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyHolidayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "holiday_name", nullable = false)
    private String holidayName;

    @Column(name = "date", nullable = false, unique = true)
    private LocalDate date;

    @Column(name = "description")
    private String description;
}
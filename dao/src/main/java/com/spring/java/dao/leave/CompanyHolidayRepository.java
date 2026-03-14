package com.spring.java.dao.leave;


import com.spring.java.dao.model.sql.CompanyHolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompanyHolidayRepository
        extends JpaRepository<CompanyHolidayEntity, Long> {

    List<CompanyHolidayEntity> findByDateBetween(LocalDate start, LocalDate end);
}
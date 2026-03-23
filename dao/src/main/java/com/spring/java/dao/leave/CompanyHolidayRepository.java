package com.spring.java.dao.leave;

import com.spring.java.dao.model.sql.CompanyHolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompanyHolidayRepository
        extends JpaRepository<CompanyHolidayEntity, Long> {

    List<CompanyHolidayEntity> findByDateBetween(LocalDate start, LocalDate end);
}
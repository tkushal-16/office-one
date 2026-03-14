package com.spring.java.dao.util;


import com.spring.java.common.leave.LeaveDayType;
import com.spring.java.dao.leave.CompanyHolidayRepository;
import com.spring.java.dao.model.sql.CompanyHolidayEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LeaveCalculationService {

    private final CompanyHolidayRepository companyHolidayRepository;

    public double calculateTotalLeaveDays(
            LocalDate startDate,
            LocalDate endDate,
            LeaveDayType startDayType,
            LeaveDayType endDayType
    ) {

        // invalid range
        if (startDate.isAfter(endDate)) {
            return 0.0;
        }

        List<CompanyHolidayEntity> holidaysList =
                companyHolidayRepository.findByDateBetween(startDate, endDate);

        Set<LocalDate> holidays = new HashSet<>();

        for (CompanyHolidayEntity holiday : holidaysList) {
            holidays.add(holiday.getDate());
        }

        double totalDays = 0.0;
        LocalDate current = startDate;

        while (!current.isAfter(endDate)) {

            boolean isWeekend =
                    current.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    current.getDayOfWeek() == DayOfWeek.SUNDAY;

            boolean isHoliday = holidays.contains(current);

            if (!isWeekend && !isHoliday) {

                if (startDate.equals(endDate)) {

                    if (isHalfDay(startDayType)) {
                        totalDays += 0.5;
                    } else {
                        totalDays += 1.0;
                    }

                } else if (current.equals(startDate)) {

                    if (isHalfDay(startDayType)) {
                        totalDays += 0.5;
                    } else {
                        totalDays += 1.0;
                    }

                } else if (current.equals(endDate)) {

                    if (isHalfDay(endDayType)) {
                        totalDays += 0.5;
                    } else {
                        totalDays += 1.0;
                    }

                } else {

                    totalDays += 1.0;

                }
            }

            current = current.plusDays(1);
        }

        return totalDays;
    }

    private boolean isHalfDay(LeaveDayType type) {
        return type == LeaveDayType.FIRST_HALF || type == LeaveDayType.SECOND_HALF;
    }
}
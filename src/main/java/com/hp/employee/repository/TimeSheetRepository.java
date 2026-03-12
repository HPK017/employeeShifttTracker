package com.hp.employee.repository;

import com.hp.employee.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {
}

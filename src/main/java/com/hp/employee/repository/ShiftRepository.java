package com.hp.employee.repository;

import com.hp.employee.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

    Optional<Shift> findFirstByEmployeeIdAndEndTimeIsNullOrderByStartTimeDesc(Long employeeId);

    Optional<Shift> findByEmployeeId(Long employeeId);

    List<Shift> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}

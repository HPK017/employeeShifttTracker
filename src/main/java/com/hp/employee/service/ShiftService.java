package com.hp.employee.service;

import com.hp.employee.dto.ShiftResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShiftService {

    Page<ShiftResponseDto> getAllShifts(Pageable pageable);

    ShiftResponseDto getShiftsByEmployeeId(Long EmployeeId);
}

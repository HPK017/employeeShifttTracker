package com.hp.employee.service;

import com.hp.employee.dto.ShiftResponseDto;

import java.util.List;

public interface ShiftService {

    List<ShiftResponseDto> getAllShifts();

    ShiftResponseDto getShiftsByEmployeeId(Long EmployeeId);
}

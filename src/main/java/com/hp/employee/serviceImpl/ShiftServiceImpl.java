package com.hp.employee.serviceImpl;

import com.hp.employee.dto.ShiftResponseDto;
import com.hp.employee.entity.Employee;
import com.hp.employee.entity.Shift;
import com.hp.employee.repository.EmployeeRepository;
import com.hp.employee.repository.ShiftRepository;
import com.hp.employee.service.EmployeeService;
import com.hp.employee.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final EmployeeRepository employeeRepository;
    private final ShiftRepository shiftRepository;

    @Override
    @PreAuthorize("hasAuthority('shift:view')")
    public Page<ShiftResponseDto> getAllShifts(Pageable pageable) {
        return shiftRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    @PreAuthorize("hasAuthority('shift:view')")
    public ShiftResponseDto getShiftsByEmployeeId(Long EmployeeId) {

        Shift employeeOfShift = shiftRepository.findByEmployeeId(EmployeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return mapToResponse(employeeOfShift);
    }

    private ShiftResponseDto mapToResponse(Shift shift) {

        ShiftResponseDto dto = ShiftResponseDto
                .builder()
                .shiftId(shift.getId())
                .startTime(shift.getStartTime())
                .endTime(shift.getEndTime())
                .actualHours(shift.getActualHours())
                .build();

        return dto;
    }
}

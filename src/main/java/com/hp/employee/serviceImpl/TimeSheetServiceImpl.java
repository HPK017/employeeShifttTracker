package com.hp.employee.serviceImpl;

import com.hp.employee.dto.TimeSheetRequestDto;
import com.hp.employee.dto.TimeSheetResponseDto;
import com.hp.employee.entity.Employee;
import com.hp.employee.entity.Shift;
import com.hp.employee.entity.TimeSheet;
import com.hp.employee.repository.EmployeeRepository;
import com.hp.employee.repository.ShiftRepository;
import com.hp.employee.repository.TimeSheetRepository;
import com.hp.employee.service.TimeSheetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeSheetServiceImpl implements TimeSheetService {

    private final TimeSheetRepository timeSheetRepository;
    private final EmployeeRepository employeeRepository;
    private final ShiftRepository shiftRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('timesheet:create')")
    public TimeSheetResponseDto createTimeSheet(TimeSheetRequestDto timeSheetRequestDto) {

        Employee employee = employeeRepository.findById(timeSheetRequestDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Shift shift = shiftRepository.findById(timeSheetRequestDto.getShiftId())
                .orElseThrow(() -> new RuntimeException("Shift not found"));

        TimeSheet timeSheet = TimeSheet.builder()
                .employee(employee)
                .shift(shift)
                .projectName(timeSheetRequestDto.getProjectName())
                .taskName(timeSheetRequestDto.getTaskName())
                .fromDate(timeSheetRequestDto.getFromDate())
                .endDate(timeSheetRequestDto.getEndDate())
                .build();

        timeSheetRepository.save(timeSheet);

        return mapToResponse(timeSheet);
    }

    @Override
    @PreAuthorize("hasAuthority('timesheet:view')")
    public TimeSheetResponseDto getTimeSheetById(Long id) {

        TimeSheet timeSheet = timeSheetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TimeSheet not Found"));

        return mapToResponse(timeSheet);
    }

    @Override
    @PreAuthorize("hasAuthority('timesheet:view')")
    public Page<TimeSheetResponseDto> getAllTimeSheets(Pageable pageable) {
        return timeSheetRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('timesheet:update')")
    public TimeSheetResponseDto updateTimeSheet(Long id, TimeSheetRequestDto timeSheetRequestDto) {

        TimeSheet timeSheet = timeSheetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TimeSheet not Found"));

        Employee employee = employeeRepository.findById(timeSheetRequestDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Shift shift = shiftRepository.findById(timeSheetRequestDto.getShiftId())
                .orElseThrow(() -> new RuntimeException("Shift not found"));

        timeSheet.setEmployee(employee);
        timeSheet.setShift(shift);
        timeSheet.setProjectName(timeSheetRequestDto.getProjectName());
        timeSheet.setTaskName(timeSheet.getTaskName());
        timeSheet.setFromDate(timeSheetRequestDto.getFromDate());
        timeSheet.setEndDate(timeSheetRequestDto.getEndDate());

        return mapToResponse(timeSheet);
    }

    @Override
    @PreAuthorize("hasAuthority('timesheet:delete')")
    public void deleteTimeSheet(Long id) {

        TimeSheet timeSheet = timeSheetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TimeSheet not Found"));

        timeSheetRepository.delete(timeSheet);

    }

    private TimeSheetResponseDto mapToResponse(TimeSheet timeSheet) {

        TimeSheetResponseDto dto = TimeSheetResponseDto.builder()
                .timesheetId(timeSheet.getId())
                .employeeId(timeSheet.getEmployee().getId())
                .shiftId(timeSheet.getEmployee().getId())
                .projectName(timeSheet.getProjectName())
                .taskName(timeSheet.getTaskName())
                .fromDate(timeSheet.getFromDate())
                .endDate(timeSheet.getEndDate())
                .build();

        return dto;
    }
}

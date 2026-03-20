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
import lombok.RequiredArgsConstructor;
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
    public TimeSheetResponseDto createTimeSheet(TimeSheetRequestDto timeSheetRequestDto) {

        TimeSheet timeSheet = timeSheetRepository.findById(timeSheetRequestDto.getTimeSheetId())
                .orElse(null);

        if (timeSheet != null) throw new IllegalArgumentException("TimeSheet not Found");

        Employee employee = employeeRepository.findById(timeSheetRequestDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Shift shift = shiftRepository.findById(timeSheetRequestDto.getShiftId())
                .orElseThrow(() -> new RuntimeException("Shift not found"));

        timeSheet = TimeSheet.builder()
                .id(timeSheetRequestDto.getTimeSheetId())
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
    public TimeSheetResponseDto getTimeSheetById(Long id) {

        TimeSheet timeSheet = timeSheetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TimeSheet not Found"));

        return mapToResponse(timeSheet);
    }

    @Override
    public List<TimeSheetResponseDto> getAllTimeSheets() {
        return timeSheetRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
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
    public void deleteTimeSheet(Long id) {

        TimeSheet timeSheet = timeSheetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TimeSheet not Found"));

        timeSheetRepository.delete(timeSheet);

    }

    private TimeSheetResponseDto mapToResponse(TimeSheet timeSheet) {

        TimeSheetResponseDto dto = TimeSheetResponseDto.builder()
                .employeeId(timeSheet.getEmployee().getId())
                .shiftId(timeSheet.getEmployee().getId())
                .taskName(timeSheet.getTaskName())
                .fromDate(timeSheet.getFromDate())
                .endDate(timeSheet.getEndDate())
                .build();

        return dto;
    }
}

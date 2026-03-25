package com.hp.employee.serviceImpl;

import com.hp.employee.dto.EmployeeRequestDto;
import com.hp.employee.dto.EmployeeResponseDto;
import com.hp.employee.entity.Employee;
import com.hp.employee.exception.ResourceNotFoundException;
import com.hp.employee.repository.EmployeeRepository;
import com.hp.employee.security.JwtUtil;
import com.hp.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {

        Employee employee = employeeRepository.findByEmail(dto.getEmail()).orElse(null);

        if (employee != null) throw new IllegalArgumentException("Employee already Exists");

        employee = Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .assignedShiftHours(dto.getAssignedShiftHours())
                .role(dto.getRole())
                .build();

        Employee saved = employeeRepository.save(employee);

        return mapTOResponse(saved);
    }

    @PreAuthorize("hasAuthority('employee:read')")
    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return mapTOResponse(employee);
    }

    @PreAuthorize("hasAuthority('employee:read')")
    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::mapTOResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('employee:update')")
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employee.setAssignedShiftHours(dto.getAssignedShiftHours());
        employee.setRole(dto.getRole());

        Employee updated = employeeRepository.save(employee);

        return mapTOResponse(updated);

    }

    @PreAuthorize("hasAuthority('employee:delete')")
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);
    }


    private EmployeeResponseDto mapTOResponse(Employee employee) {

        EmployeeResponseDto dto = EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .assignedShiftHours(employee.getAssignedShiftHours())
                .role(employee.getRole())
                .build();

        System.out.println(dto);   // DEBUG

        return dto;
    }

}

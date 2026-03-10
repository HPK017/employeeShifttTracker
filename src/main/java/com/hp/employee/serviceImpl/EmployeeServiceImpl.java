package com.hp.employee.serviceImpl;

import com.hp.employee.dto.EmployeeRequestDto;
import com.hp.employee.dto.EmployeeResponseDto;
import com.hp.employee.entity.Employee;
import com.hp.employee.repository.EmployeeRepository;
import com.hp.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {

        Employee employee = Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .assignedShiftHours(dto.getAssignedShiftHours())
                .role(dto.getRole())
                .build();

        Employee saved = employeeRepository.save(employee);

        return mapTOResponse(saved);
    }

    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return mapTOResponse(employee);
    }

    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::mapTOResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword());
        employee.setAssignedShiftHours(dto.getAssignedShiftHours());
        employee.setRole(dto.getRole());

        Employee updated = employeeRepository.save(employee);

        return mapTOResponse(updated);

    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);
    }


    private EmployeeResponseDto mapTOResponse(Employee employee) {

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .assignShiftedHours(employee.getAssignedShiftHours())
                .role(employee.getRole())
                .build();
    }

}

package com.hp.employee.service;


import com.hp.employee.dto.EmployeeRequestDto;
import com.hp.employee.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto empDto);

    EmployeeResponseDto getEmployeeById(Long id);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto empDto);

    void deleteEmployee(Long id);

}

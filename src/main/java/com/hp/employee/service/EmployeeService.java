package com.hp.employee.service;


import com.hp.employee.dto.EmployeeRequestDto;
import com.hp.employee.dto.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto empDto);

    EmployeeResponseDto getEmployeeById(Long id);

    Page<EmployeeResponseDto> getAllEmployees(Pageable pageable);

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto empDto);

    void deleteEmployee(Long id);

}

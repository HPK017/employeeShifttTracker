package com.hp.employee.controller;

import com.hp.employee.dto.EmployeeLoginRequestDto;
import com.hp.employee.dto.EmployeeLoginResponseDto;
import com.hp.employee.dto.EmployeeRequestDto;
import com.hp.employee.dto.EmployeeResponseDto;
import com.hp.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/register")
    public EmployeeResponseDto register(@RequestBody EmployeeRequestDto employeeRequestDto) {
        return employeeService.createEmployee(employeeRequestDto);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeId(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable Long id,
                                              @RequestBody EmployeeRequestDto employeeRequestDto) {
        return employeeService.updateEmployee(id, employeeRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}

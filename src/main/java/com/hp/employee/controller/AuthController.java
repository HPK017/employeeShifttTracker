package com.hp.employee.controller;

import com.hp.employee.dto.EmployeeLoginRequestDto;
import com.hp.employee.dto.EmployeeLoginResponseDto;
import com.hp.employee.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public EmployeeLoginResponseDto login(@RequestBody EmployeeLoginRequestDto employeeLoginRequestDto) {
        return authService.loginEmployee(employeeLoginRequestDto);
    }
}

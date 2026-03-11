package com.hp.employee.service;

import com.hp.employee.dto.EmployeeLoginRequestDto;
import com.hp.employee.dto.EmployeeLoginResponseDto;

public interface AuthService {

    EmployeeLoginResponseDto loginEmployee(EmployeeLoginRequestDto empLogDto);

}

package com.hp.employee.service;

import com.hp.employee.dto.*;

public interface AuthService {

    EmployeeLoginResponseDto loginEmployee(EmployeeLoginRequestDto empLogDto);

    LogOutResponseDto logOutEmployee(LogoutRequestDto empDto);

    EmployeeLoginResponseDto refreshToken(String refreshToken);

}

package com.hp.employee.controller;

import com.hp.employee.dto.*;
import com.hp.employee.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<EmployeeLoginResponseDto> login(
                                                          @RequestBody EmployeeLoginRequestDto employeeLoginRequestDto,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response) {
        EmployeeLoginResponseDto employeeLoginResponseDto =  authService.loginEmployee(employeeLoginRequestDto);
        Cookie cookie = new Cookie("refreshToken", employeeLoginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(employeeLoginResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogOutResponseDto> logOut(HttpServletRequest httpServletRequest,
                                                    HttpServletResponse httpServletResponse,
                                                    @RequestBody LogoutRequestDto logoutRequestDto) {

        String refrshToken = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);

        LogOutResponseDto responseDto = authService.logOutEmployee(logoutRequestDto, refrshToken);

        //Delete Cookie
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<EmployeeLoginResponseDto> refreshToken(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("RefreshToken not found"));
        EmployeeLoginResponseDto employeeLoginResponseDto = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(employeeLoginResponseDto);
    }
}

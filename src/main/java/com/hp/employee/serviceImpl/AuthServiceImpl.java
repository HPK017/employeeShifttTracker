package com.hp.employee.serviceImpl;

import com.hp.employee.dto.EmployeeLoginRequestDto;
import com.hp.employee.dto.EmployeeLoginResponseDto;
import com.hp.employee.entity.Employee;
import com.hp.employee.repository.EmployeeRepository;
import com.hp.employee.security.JwtUtil;
import com.hp.employee.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public EmployeeLoginResponseDto loginEmployee(EmployeeLoginRequestDto empLogDto) {

        Employee employee = employeeRepository.findByEmail(empLogDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Emplyoee not found"));


        if (!passwordEncoder.matches(empLogDto.getPassword(), employee.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(employee.getEmail());

        return EmployeeLoginResponseDto.builder()
                .token(token)
                .email(employee.getEmail())
                .role(employee.getRole())
                .build();
    }
}

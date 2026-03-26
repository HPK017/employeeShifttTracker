package com.hp.employee.serviceImpl;

import com.hp.employee.dto.EmployeeLoginRequestDto;
import com.hp.employee.dto.EmployeeLoginResponseDto;
import com.hp.employee.dto.LogOutResponseDto;
import com.hp.employee.dto.LogoutRequestDto;
import com.hp.employee.entity.Employee;
import com.hp.employee.entity.Shift;
import com.hp.employee.exception.ResourceNotFoundException;
import com.hp.employee.repository.EmployeeRepository;
import com.hp.employee.repository.ShiftRepository;
import com.hp.employee.security.JwtUtil;
import com.hp.employee.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;
    private final ShiftRepository shiftRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public EmployeeLoginResponseDto loginEmployee(EmployeeLoginRequestDto empLogDto) {

        Employee employee = employeeRepository.findByEmail(empLogDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Emplyoee not found"));


        if (!passwordEncoder.matches(empLogDto.getPassword(), employee.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        Shift shift = Shift.builder()
                .employee(employee)
                .startTime(LocalDateTime.now())
                .build();

        shiftRepository.save(shift);

        String accessToken = jwtUtil.generateToken(employee.getEmail());
        String refreshToken = jwtUtil.createRefreshToken(employee.getEmail());

        return EmployeeLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(employee.getEmail())
                .role(employee.getRole())
                .build();
    }

    @Override
    public LogOutResponseDto logOutEmployee(LogoutRequestDto dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Shift shift = shiftRepository
                .findFirstByEmployeeIdAndEndTimeIsNullOrderByStartTimeDesc(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("No active shift found"));

        shift.setEndTime(LocalDateTime.now());
        shift.calculateActualHours();
        shiftRepository.save(shift);

        return LogOutResponseDto.builder()
                .shiftId(shift.getId())
                .startTime(shift.getStartTime())
                .endTime((shift.getEndTime()))
                .actualHours(shift.getActualHours())
                .message("Logout Successful")
                .build();

    }

    @Override
    public EmployeeLoginResponseDto refreshToken(String refreshToken) {
        String email = jwtUtil.extractEmail(refreshToken);
        //Optional<Employee> employee = employeeRepository.findByEmail(email);

        String accessToken = jwtUtil.createRefreshToken(email);

        return EmployeeLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(email)
                .build();
    }
}

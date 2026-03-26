package com.hp.employee.dto;

import com.hp.employee.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeLoginResponseDto {

    private String accessToken;
    private String refreshToken;
    private String email;
    private Role role;
}

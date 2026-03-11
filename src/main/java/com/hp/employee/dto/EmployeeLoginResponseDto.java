package com.hp.employee.dto;

import com.hp.employee.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeLoginResponseDto {

    private String token;
    private String email;
    private Role role;
}

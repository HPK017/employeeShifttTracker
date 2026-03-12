package com.hp.employee.dto;

import com.hp.employee.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String email;
    //private String password;
    private Integer assignedShiftHours;
    private Role role;
}

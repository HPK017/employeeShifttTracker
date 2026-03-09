package com.hp.employee.dto;

import com.hp.employee.enums.Role;
import lombok.Data;

@Data
public class EmployeeRequestDto {
    private String name;
    private String email;
    private String password;
    private int assignShiftedHours;
    private Role role;
}

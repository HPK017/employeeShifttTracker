package com.hp.employee.dto;

import lombok.Data;

@Data
public class EmployeeLoginRequestDto {

    private String email;
    private String password;
}

package com.hp.employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClaimResponseDto {
    private Long id;
    private String keyC;
    private String valueC;
    private Long employeeId;
}

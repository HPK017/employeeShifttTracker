package com.hp.employee.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportingRequestDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

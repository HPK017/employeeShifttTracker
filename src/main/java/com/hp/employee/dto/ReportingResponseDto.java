package com.hp.employee.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReportingResponseDto {

    private String employeeName;
    private Integer assignedShiftHours;
    private Float actualHours;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

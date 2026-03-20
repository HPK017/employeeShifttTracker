package com.hp.employee.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TimeSheetResponseDto {

    private Long employeeId;
    private Long shiftId;
    private String projectName;
    private String taskName;
    private LocalDateTime fromDate;
    private LocalDateTime endDate;
}

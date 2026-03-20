package com.hp.employee.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShiftResponseDto {
    private Long shiftId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Float actualHours;
}

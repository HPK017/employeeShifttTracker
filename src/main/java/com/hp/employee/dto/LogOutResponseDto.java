package com.hp.employee.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class LogOutResponseDto {
    private Long shiftId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Float actualHours;
    private String message;
}

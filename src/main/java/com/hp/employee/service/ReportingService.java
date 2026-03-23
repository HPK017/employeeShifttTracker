package com.hp.employee.service;

import com.hp.employee.dto.ReportingRequestDto;
import com.hp.employee.dto.ReportingResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportingService {

    List<ReportingResponseDto> generateReport(LocalDateTime start, LocalDateTime end);

    byte[] generateReportExcel(LocalDateTime start, LocalDateTime end);
}

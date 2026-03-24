package com.hp.employee.controller;

import com.hp.employee.dto.ReportingRequestDto;
import com.hp.employee.dto.ReportingResponseDto;
import com.hp.employee.service.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportingController {

    private final ReportingService reportingService;

        @PostMapping("/generate")
    public List<ReportingResponseDto> generateReport(@RequestBody ReportingRequestDto reportingRequestDto) {

        return reportingService.generateReport(reportingRequestDto.getStartDate(),
                reportingRequestDto.getEndDate());
    }

    @PostMapping("/generate/excel")
    public ResponseEntity<byte[]> generateExcel(@RequestBody ReportingRequestDto reportingRequestDto) {
        byte[] excel = reportingService.generateReportExcel(
                reportingRequestDto.getStartDate(),
                reportingRequestDto.getEndDate()
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=report.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }
}

package com.hp.employee.serviceImpl;

import com.hp.employee.dto.ReportingResponseDto;
import com.hp.employee.entity.Shift;
import com.hp.employee.repository.ShiftRepository;
import com.hp.employee.service.ReportingService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportingServiceImpl implements ReportingService {

    private final ShiftRepository shiftRepository;

    @Override
    public List<ReportingResponseDto> generateReport(LocalDateTime start, LocalDateTime end) {

        List<Shift> shifts = shiftRepository.findByStartTimeBetween(start, end);

        return shifts.stream().map(shift ->
                ReportingResponseDto.builder()
                        .employeeName(shift.getEmployee().getName())
                        .assignedShiftHours(shift.getEmployee().getAssignedShiftHours())
                        .actualHours(shift.getActualHours())
                        .startTime(shift.getStartTime())
                        .endTime(shift.getEndTime())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public byte[] generateReportExcel(LocalDateTime start, LocalDateTime end) {

        try {
            List<ReportingResponseDto> reportData = generateReport(start, end);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Report");

            //Header
            Row header = sheet.createRow(0);
            String[] headers = {
                    "Employee Name",
                    "Assigned Shift Hours",
                    "Actual Hours",
                    "Start Time",
                    "End Time"
            };

            for(int i=0; i < headers.length; i++) {
                header.createCell(i).setCellValue(headers[i]);
            }

            //Data
            int rowNum = 1;
            for(ReportingResponseDto data : reportData) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(data.getEmployeeName());
                row.createCell(1).setCellValue(data.getAssignedShiftHours());
                row.createCell(2).setCellValue(data.getActualHours() != null
                ? data.getAssignedShiftHours() : 0);
                row.createCell(3).setCellValue(data.getStartTime().toString());
                row.createCell(4).setCellValue(data.getEndTime() != null
                        ? data.getEndTime().toString() : "");
            }

            for(int i=0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            //convert to byte[]
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error Generating Excel Report", e);
        }
    }
}

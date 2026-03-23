package com.hp.employee.controller;

import com.hp.employee.dto.TimeSheetRequestDto;
import com.hp.employee.dto.TimeSheetResponseDto;
import com.hp.employee.service.TimeSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheet")
@RequiredArgsConstructor
public class TimeSheetController {

    private final TimeSheetService timeSheetService;

    @PostMapping
    public TimeSheetResponseDto createTimeSheet(@RequestBody TimeSheetRequestDto timeSheetRequestDto) {
        return timeSheetService.createTimeSheet(timeSheetRequestDto);
    }

    @GetMapping("/{id}")
    public TimeSheetResponseDto getTimeSheetById(@PathVariable Long id) {
        return timeSheetService.getTimeSheetById(id);
    }

    @GetMapping
    public List<TimeSheetResponseDto> getTimeSheets() {
        return timeSheetService.getAllTimeSheets();
    }

    @PutMapping("/{id}")
    public TimeSheetResponseDto updateTimeSheet(@PathVariable Long id, @RequestBody
                                                TimeSheetRequestDto timeSheetRequestDto) {
        return timeSheetService.updateTimeSheet(id, timeSheetRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteTimeSheet(@PathVariable Long id){
        timeSheetService.deleteTimeSheet(id);
        return "TimeSheet Deleted Successfully";
    }

}

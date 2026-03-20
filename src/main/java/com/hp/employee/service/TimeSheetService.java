package com.hp.employee.service;


import com.hp.employee.dto.TimeSheetRequestDto;
import com.hp.employee.dto.TimeSheetResponseDto;

import java.util.List;

public interface TimeSheetService {

    TimeSheetResponseDto createTimeSheet(TimeSheetRequestDto timeSheetRequestDto);

    TimeSheetResponseDto getTimeSheetById(Long id);

    List<TimeSheetResponseDto> getAllTimeSheets();

    TimeSheetResponseDto updateTimeSheet(Long id, TimeSheetRequestDto timeSheetRequestDto);

    void deleteTimeSheet(Long id);
}

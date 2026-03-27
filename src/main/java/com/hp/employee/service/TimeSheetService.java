package com.hp.employee.service;


import com.hp.employee.dto.TimeSheetRequestDto;
import com.hp.employee.dto.TimeSheetResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TimeSheetService {

    TimeSheetResponseDto createTimeSheet(TimeSheetRequestDto timeSheetRequestDto);

    TimeSheetResponseDto getTimeSheetById(Long id);

    Page<TimeSheetResponseDto> getAllTimeSheets(Pageable pageable);

    TimeSheetResponseDto updateTimeSheet(Long id, TimeSheetRequestDto timeSheetRequestDto);

    void deleteTimeSheet(Long id);
}

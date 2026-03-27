package com.hp.employee.controller;

import com.hp.employee.dto.EmployeeResponseDto;
import com.hp.employee.dto.ShiftResponseDto;
import com.hp.employee.repository.ShiftRepository;
import com.hp.employee.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;


    @GetMapping("/employee/{id}")
    public Page<ShiftResponseDto> getEmployeeId(@PathVariable Long id,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(
                page,
                size
        );
        return shiftService.getShiftsByEmployeeId(id, pageable);
    }

    @GetMapping("/getAllShifts")
    public ResponseEntity<Page<ShiftResponseDto>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size
        );

        Page<ShiftResponseDto> result = shiftService.getAllShifts(pageable);
        return ResponseEntity.ok(result);
    }
}

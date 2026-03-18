package com.hp.employee.controller;

import com.hp.employee.dto.EmployeeResponseDto;
import com.hp.employee.dto.ShiftResponseDto;
import com.hp.employee.repository.ShiftRepository;
import com.hp.employee.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;


    @GetMapping("/employee/{id}")
    public ShiftResponseDto getEmployeeId(@PathVariable Long id) {
        return shiftService.getShiftsByEmployeeId(id);
    }

    @GetMapping("/getAllShifts")
    public List<ShiftResponseDto> getAllEmployees() {
        return shiftService.getAllShifts();
    }
}

package com.hp.employee.controller;

import com.hp.employee.dto.ClaimRequestDto;
import com.hp.employee.dto.ClaimResponseDto;
import com.hp.employee.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ClaimResponseDto createClaim(@RequestBody ClaimRequestDto dto) {
        return claimService.createClaim(dto);
    }

    @GetMapping
    public List<ClaimResponseDto> getAllClaims() {
        return claimService.getAllClaims();
    }

    @GetMapping("/employee/{id}")
    public List<ClaimResponseDto> getAllClaimsOfEmployee(@PathVariable Long id) {
        return claimService.getClaimsByEmployee(id);
    }

    @GetMapping("/{id}")
    public ClaimResponseDto getClaimsById(@PathVariable Long id) {
        return claimService.getClaimById(id);
    }

    @PutMapping("/{id}")
    public ClaimResponseDto updateClaim(@PathVariable Long id,
                                        @RequestBody ClaimRequestDto claimRequestDto) {
        return claimService.updateClaim(id, claimRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return "Claim Deleted Successfully";
    }
}

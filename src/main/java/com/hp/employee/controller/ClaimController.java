package com.hp.employee.controller;

import com.hp.employee.dto.ClaimRequestDto;
import com.hp.employee.dto.ClaimResponseDto;
import com.hp.employee.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<ClaimResponseDto>> getAllClaims(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(
                page,
                size
        );

        Page<ClaimResponseDto> result = claimService.getAllClaims(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employee/{id}")
    public Page<ClaimResponseDto> getAllClaimsOfEmployee(@PathVariable Long id, Pageable pageable) {
        return claimService.getClaimsByEmployee(id, pageable);
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

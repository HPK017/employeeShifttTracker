package com.hp.employee.service;

import com.hp.employee.dto.ClaimRequestDto;
import com.hp.employee.dto.ClaimResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClaimService {

    ClaimResponseDto createClaim(ClaimRequestDto claimRequestDto);

    ClaimResponseDto getClaimById(Long id);

    Page<ClaimResponseDto> getClaimsByEmployee(Long employeeId, Pageable pageable);

    Page<ClaimResponseDto> getAllClaims(Pageable pageable);

    ClaimResponseDto updateClaim(Long id, ClaimRequestDto claimRequestDto);

    void deleteClaim(Long id);

}

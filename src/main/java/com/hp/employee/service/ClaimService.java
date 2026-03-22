package com.hp.employee.service;

import com.hp.employee.dto.ClaimRequestDto;
import com.hp.employee.dto.ClaimResponseDto;

import java.util.List;

public interface ClaimService {

    ClaimResponseDto createClaim(ClaimRequestDto claimRequestDto);

    ClaimResponseDto getClaimById(Long id);

    List<ClaimResponseDto> getClaimsByEmployee(Long employeeId);

    List<ClaimResponseDto> getAllClaims();

    ClaimResponseDto updateClaim(Long id, ClaimRequestDto claimRequestDto);

    void deleteClaim(Long id);

}

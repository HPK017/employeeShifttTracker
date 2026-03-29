package com.hp.employee.serviceImpl;

import com.hp.employee.dto.ClaimRequestDto;
import com.hp.employee.dto.ClaimResponseDto;
import com.hp.employee.entity.Claim;
import com.hp.employee.entity.Employee;
import com.hp.employee.repository.ClaimRepository;
import com.hp.employee.repository.EmployeeRepository;
import com.hp.employee.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @PreAuthorize("hasAuthority('claim:create')")
    public ClaimResponseDto createClaim(ClaimRequestDto claimRequestDto) {

        Employee employee = employeeRepository.findById(claimRequestDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Claim claim = Claim.builder()
                .keyC(claimRequestDto.getKeyC())
                .valueC(claimRequestDto.getValueC())
                .employee(employee)
                .build();

        claimRepository.save(claim);

        return mapToResponse(claim);
    }

    @Override
    @PreAuthorize("hasAuthority('claim:view')")
    public ClaimResponseDto getClaimById(Long id) {

        Claim claim = claimRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Claim not Found"));

        return mapToResponse(claim);
    }

    @Override
    @PreAuthorize("hasAuthority('claim:view')")
    public Page<ClaimResponseDto> getClaimsByEmployee(Long employeeId, Pageable pageable) {
        return claimRepository.findByEmployeeId(employeeId, pageable)
                .map(this::mapToResponse);
    }

    @Override
    @PreAuthorize("hasAuthority('claim:view')")
    public Page<ClaimResponseDto> getAllClaims(Pageable pageable) {
        return claimRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    @PreAuthorize("hasAuthority('claim:update')")
    public ClaimResponseDto updateClaim(Long id, ClaimRequestDto claimRequestDto) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim id not found"));

        Employee employee = employeeRepository.findById(claimRequestDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        claim.setKeyC(claimRequestDto.getKeyC());
        claim.setValueC(claimRequestDto.getValueC());
        claim.setEmployee(employee);

        claimRepository.save(claim);

        return mapToResponse(claim);
    }

    @Override
    @PreAuthorize("hasAuthority('claim:delete')")
    public void deleteClaim(Long id) {
        Claim claim = claimRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Claim not found"));
        claimRepository.delete(claim);
    }

    private ClaimResponseDto mapToResponse(Claim claim) {
        return ClaimResponseDto.builder()
                .id(claim.getId())
                .keyC(claim.getKeyC())
                .valueC(claim.getValueC())
                .employeeId(claim.getEmployee().getId())
                .build();
    }

}

package com.hp.employee.serviceImpl;

import com.hp.employee.dto.ClaimRequestDto;
import com.hp.employee.dto.ClaimResponseDto;
import com.hp.employee.entity.Claim;
import com.hp.employee.entity.Employee;
import com.hp.employee.repository.ClaimRepository;
import com.hp.employee.repository.EmployeeRepository;
import com.hp.employee.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final EmployeeRepository employeeRepository;

    @Override
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
    public ClaimResponseDto getClaimById(Long id) {

        Claim claim = claimRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Claim not Found"));

        return mapToResponse(claim);
    }

    @Override
    public List<ClaimResponseDto> getClaimsByEmployee(Long employeeId) {
        return claimRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClaimResponseDto> getAllClaims() {
        return claimRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
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

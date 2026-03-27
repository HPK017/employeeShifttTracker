package com.hp.employee.repository;

import com.hp.employee.entity.Claim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Page<Claim> findByEmployeeId(Long employeeId, Pageable pageable);
}

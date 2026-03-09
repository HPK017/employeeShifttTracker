package com.hp.employee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String keyC;

    @Column(nullable = false)
    private String valueC;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    private Employee employee;

}

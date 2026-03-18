package com.hp.employee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    private Employee employee;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Float actualHours;

    public void calculateActualHours() {
        if(startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            this.actualHours = duration.toMinutes() / 60.0f;
        }
    }


}

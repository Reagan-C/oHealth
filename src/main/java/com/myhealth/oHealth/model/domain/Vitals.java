package com.myhealth.oHealth.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vitals")
public class Vitals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "temperature", nullable = false)
    private double temperature;

    @Column(name = "blood_pressure", nullable = false)
    private String bloodPressure;

    @Column(name = "weight", nullable = false)
    private String weight;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checked_on", nullable = false)
    private LocalDateTime createdOn;

}

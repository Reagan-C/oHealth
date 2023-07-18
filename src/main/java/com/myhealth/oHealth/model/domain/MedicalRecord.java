package com.myhealth.oHealth.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "condition_name")
    private String condition;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_on", nullable = false)
    private LocalDate createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_on", nullable = false)
    private LocalDate updatedOn;
}

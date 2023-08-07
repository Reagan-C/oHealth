package com.myhealth.oHealth.repository;

import com.myhealth.oHealth.model.domain.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalsRepository extends JpaRepository<Vitals, Long> {
}

package com.myhealth.oHealth.repository;

import com.myhealth.oHealth.model.domain.FrontDeskOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrontDeskOfficerRepository extends JpaRepository<FrontDeskOfficer, Long> {
}

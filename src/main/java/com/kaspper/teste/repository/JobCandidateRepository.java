package com.kaspper.teste.repository;

import com.kaspper.teste.model.JobCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCandidateRepository extends JpaRepository<JobCandidate, Long> {
}

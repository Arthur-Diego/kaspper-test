package com.kaspper.teste.repository;

import com.kaspper.teste.model.Candidate;
import com.kaspper.teste.model.Job;
import com.kaspper.teste.model.JobCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCandidateRepository extends JpaRepository<JobCandidate, Long> {

    public void deleteByJob(Job job);

    public void deleteByCandidate(Candidate candidate);

}

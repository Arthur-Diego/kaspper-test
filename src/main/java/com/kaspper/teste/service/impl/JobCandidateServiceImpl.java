package com.kaspper.teste.service.impl;

import com.kaspper.teste.model.JobCandidate;
import com.kaspper.teste.repository.JobCandidateRepository;
import com.kaspper.teste.service.JobCandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobCandidateServiceImpl implements JobCandidateService {

    private final JobCandidateRepository repository;

    @Override
    public void save(JobCandidate jobCandidate) {
        repository.save(jobCandidate);
    }
}

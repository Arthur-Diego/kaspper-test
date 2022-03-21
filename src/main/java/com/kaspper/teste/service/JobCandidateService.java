package com.kaspper.teste.service;

import com.kaspper.teste.model.Candidate;
import com.kaspper.teste.model.Job;
import com.kaspper.teste.model.JobCandidate;

public interface JobCandidateService {

    void save(JobCandidate jobCandidate);

    void removeByJob(Job job);

    void removeByCandidate(Candidate candidate);

}

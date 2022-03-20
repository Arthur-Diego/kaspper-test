package com.kaspper.teste.service.impl;

import com.kaspper.teste.dto.PageDTO;
import com.kaspper.teste.dto.request.JobRequestDTO;
import com.kaspper.teste.dto.response.JobResponseDTO;
import com.kaspper.teste.exception.EntityNotFoundException;
import com.kaspper.teste.model.Job;
import com.kaspper.teste.repository.JobRespository;
import com.kaspper.teste.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRespository jobRespository;

    @Override
    public Job findById(Long id) {
        return jobRespository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job not found!"));
    }

    @Override
    public List<JobResponseDTO> findAll() {
        return null;
    }

    @Override
    public List<JobResponseDTO> findFilter(JobRequestDTO dto) {
        return null;
    }

    @Override
    public PageDTO<JobResponseDTO> findPageable(Pageable pageable) {
        return null;
    }

    @Override
    public void save(JobRequestDTO dto) {

    }

    @Override
    public void remove(Long id) {

    }
}

package com.kaspper.teste.service;

import com.kaspper.teste.dto.PageDTO;
import com.kaspper.teste.dto.request.JobRequestDTO;
import com.kaspper.teste.dto.response.JobResponseDTO;
import com.kaspper.teste.model.Job;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {

    public Job findEntityById(Long id);

    public JobResponseDTO findById(Long id);

    public List<JobResponseDTO> findAll();

    public List<JobResponseDTO> findFilter(JobRequestDTO dto);

    public PageDTO<JobResponseDTO> findPageable(Pageable pageable);

    public void save(JobRequestDTO dto);

    public void remove(Long id);

}

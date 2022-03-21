package com.kaspper.teste.service.impl;

import com.kaspper.teste.builder.JobBuilder;
import com.kaspper.teste.builder.PageBuilder;
import com.kaspper.teste.dto.PageDTO;
import com.kaspper.teste.dto.request.JobRequestDTO;
import com.kaspper.teste.dto.response.JobResponseDTO;
import com.kaspper.teste.exception.EntityNotFoundException;
import com.kaspper.teste.model.Job;
import com.kaspper.teste.repository.JobRespository;
import com.kaspper.teste.service.JobCandidateService;
import com.kaspper.teste.service.JobService;
import com.kaspper.teste.specification.JobSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRespository repository;
    private final JobBuilder jobBuilder;

    private final JobCandidateService jobCandidateService;

    @Override
    public Job findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job not found!"));
    }

    @Override
    public JobResponseDTO findById(Long id) {

        Job job = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job not found!"));
        return jobBuilder.buildResponse(job);
    }

    @Override
    public List<JobResponseDTO> findAll() {
        return repository.findAll().stream().map(jobBuilder::buildResponse).collect(Collectors.toList());
    }

    @Override
    public List<JobResponseDTO> findFilter(JobRequestDTO dto) {
        return repository.findAll(JobSpecification.candidateSpecification(dto)).stream().map(jobBuilder::buildResponse).collect(Collectors.toList());
    }

    @Override
    public PageDTO<JobResponseDTO> findPageable(Pageable pageable) {

        Pageable pageableAux = PageBuilder.from(pageable.getPageNumber(), pageable.getPageSize());
        Page<Job> page = repository.findAll(pageableAux);
        List<JobResponseDTO> pageContentDto = getListResponseDTO(page.getContent());

        return PageDTO.buildPageDTO((long) pageable.getPageNumber(), page, pageContentDto);
    }

    private List<JobResponseDTO> getListResponseDTO(List<Job> jobs) {

        return jobs.stream()
                .map(c -> jobBuilder.buildResponse(c))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(JobRequestDTO dto) {

        log.info("[m=save] - Find or verify job...");

        if (dto.getId() != null) {
            repository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Job not found"));
        }

        log.info("[m=save] - Save job...");
        repository.save(jobBuilder.build(dto));
    }

    @Override
    @Transactional
    public void remove(Long id) {

        log.info("[m=remove] - Find job...");
        Job job = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        log.info("[m=remove] - Job remove relationship with JobCandidate");
        jobCandidateService.removeByJob(job);

        log.info("[m=remove] - Job...");
        repository.delete(job);
    }
}

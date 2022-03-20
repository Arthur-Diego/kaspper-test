package com.kaspper.teste.service.impl;

import com.kaspper.teste.builder.CandidateBuilder;
import com.kaspper.teste.builder.PageBuilder;
import com.kaspper.teste.dto.PageDTO;
import com.kaspper.teste.dto.request.CandidateRequestDTO;
import com.kaspper.teste.dto.response.CandidateResponseDTO;
import com.kaspper.teste.exception.EntityNotFoundException;
import com.kaspper.teste.model.Candidate;
import com.kaspper.teste.model.Job;
import com.kaspper.teste.model.JobCandidate;
import com.kaspper.teste.repository.CandidateRepository;
import com.kaspper.teste.service.CandidateService;
import com.kaspper.teste.service.JobCandidateService;
import com.kaspper.teste.service.JobService;
import com.kaspper.teste.specification.CandidateSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository repository;
    private final CandidateBuilder candidateBuilder;

    private final JobService jobService;
    private final JobCandidateService jobCandidateService;

    @Override
    public CandidateResponseDTO findBydId(Long idCandidate) {

        Candidate candidate = repository.findById(idCandidate).orElseThrow(() -> new EntityNotFoundException("Candidate not found"));
        return candidateBuilder.buildResponse(candidate);
    }

    @Override
    public List<CandidateResponseDTO> findAll() {
        return repository.findAll().stream().map(candidateBuilder::buildResponseWithoutCurriculum).collect(Collectors.toList());
    }

    @Override
    public List<CandidateResponseDTO> findFilter(CandidateRequestDTO dto) {
        return repository.findAll(CandidateSpecification.candidateSpecification(dto)).stream().map(candidateBuilder::buildResponseWithoutCurriculum).collect(Collectors.toList());
    }

    @Override
    public PageDTO<CandidateResponseDTO> findPageable(Pageable pageable) {

        Pageable pageableAux = PageBuilder.from(pageable.getPageNumber(), pageable.getPageSize());
        Page<Candidate> page = repository.findAll(pageableAux);
        List<CandidateResponseDTO> pageContentDto = getListResponseDTO(page.getContent());

        return PageDTO.buildPageDTO((long) pageable.getPageNumber(), page, pageContentDto);
    }

    private List<CandidateResponseDTO> getListResponseDTO(List<Candidate> candidates) {

        return candidates.stream()
                .map(c -> candidateBuilder.buildResponseWithoutCurriculum(c))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(CandidateRequestDTO dto) {

        log.info("[m=save] - Save candidate...");
        if (dto.getId() != null) {
            repository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Candidate not found"));
        }

        Candidate candidate = repository.save(candidateBuilder.build(dto));

        if(Objects.nonNull(dto.getJob()) && !dto.getJob().isEmpty()){
            dto.getJob().forEach(idJob -> {
                Job job = jobService.findById(idJob);

                JobCandidate jobCandidate =  JobCandidate.builder()
                        .candidate(candidate)
                        .job(job)
                        .build();
                jobCandidateService.save(jobCandidate);
            });
        }
    }

    @Override
    public void remove(Long id) {

        log.info("[m=remove] - Remove candidate...");
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found"));

        repository.delete(candidate);
    }
}

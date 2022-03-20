package com.kaspper.teste.service;

import com.kaspper.teste.dto.PageDTO;
import com.kaspper.teste.dto.request.CandidateRequestDTO;
import com.kaspper.teste.dto.response.CandidateResponseDTO;
import com.kaspper.teste.model.Candidate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CandidateService {

    public CandidateResponseDTO findBydId(Long idCandidate);

    public List<CandidateResponseDTO> findAll();

    public List<CandidateResponseDTO> findFilter(CandidateRequestDTO dto);

    public PageDTO<CandidateResponseDTO> findPageable(Pageable pageable);

    public void save(CandidateRequestDTO dto);

    public void remove(Long id);

}

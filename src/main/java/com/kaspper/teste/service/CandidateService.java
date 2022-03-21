package com.kaspper.teste.service;

import com.kaspper.teste.dto.PageDTO;
import com.kaspper.teste.dto.request.CandidateRequestDTO;
import com.kaspper.teste.dto.response.CandidateResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CandidateService {

    public CandidateResponseDTO findBydId(Long idCandidate);

    public List<CandidateResponseDTO> findAll();

    public List<CandidateResponseDTO> findFilter(CandidateRequestDTO dto);

    public PageDTO<CandidateResponseDTO> findPageable(Pageable pageable);

    public void save(CandidateRequestDTO dto);

    public void update(Long id, CandidateRequestDTO dto);

    public void remove(Long id);

}

package com.kaspper.teste.builder;

import com.kaspper.teste.dto.request.CandidateRequestDTO;
import com.kaspper.teste.dto.response.CandidateResponseDTO;
import com.kaspper.teste.model.Candidate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CandidateBuilder {

    public Candidate build(CandidateRequestDTO dto){
        return Candidate.builder()
                .id(dto.getId())
                .curriculum(getArrayByte(dto.getCurriculum()))
                .cpf(dto.getCpf())
                .name(dto.getName())
                .profile(dto.getProfile())
                .salaryExpectation(dto.getSalaryExpectation())
                .build();
    }

    public CandidateResponseDTO buildResponse(Candidate candidate){
        return CandidateResponseDTO.builder()
                .curriculum(candidate.getCurriculum())
                .id(candidate.getId())
                .cpf(candidate.getCpf())
                .name(candidate.getName())
                .profile(candidate.getProfile())
                .salaryExpectation(candidate.getSalaryExpectation())
                .build();
    }

    public CandidateResponseDTO buildResponseWithoutCurriculum(Candidate candidate){
        return CandidateResponseDTO.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .profile(candidate.getProfile())
                .salaryExpectation(candidate.getSalaryExpectation())
                .build();
    }

    private byte[] getArrayByte(String encondingFile){
        if(Objects.nonNull(encondingFile) && !encondingFile.isEmpty()){
            return encondingFile.getBytes();
        }
        return null;
    }
}

package com.kaspper.teste.dto.response;

import com.kaspper.teste.model.type.CandidateProfileEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CandidateResponseDTO {

    private Long id;

    private String name;

    private String cpf;

    private CandidateProfileEnum profile;

    private Double salaryExpectation;

    private byte[] curriculum;
}

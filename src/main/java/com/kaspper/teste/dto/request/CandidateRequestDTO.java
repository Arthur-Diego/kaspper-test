package com.kaspper.teste.dto.request;

import com.kaspper.teste.annotation.FieldEntity;
import com.kaspper.teste.model.type.CandidateProfileEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.List;

@Getter
@Setter
public class CandidateRequestDTO {

    @FieldEntity(isNotString = true)
    private Long id;

    private String name;

    private String cpf;
    
    @FieldEntity(isNotString = true)
    private CandidateProfileEnum profile;

    private String salaryExpectation;

    private String curriculum;

    @FieldEntity(isForeignKey = true)
    private List<Long> job;

}

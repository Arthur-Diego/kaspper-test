package com.kaspper.teste.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kaspper.teste.annotation.FieldEntity;
import com.kaspper.teste.model.type.CandidateProfileEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CandidateRequestDTO {

    @FieldEntity(isNotString = true)
    @NotNull(groups = UpdateEntityGroup.class)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String cpf;

    @FieldEntity(isNotString = true)
    private CandidateProfileEnum profile;

    @NotNull
    private Double salaryExpectation;

    @NotNull
    private String curriculum;

    @FieldEntity(isForeignKey = true)
    private List<Long> job;

}

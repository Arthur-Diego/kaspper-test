package com.kaspper.teste.dto.request;

import com.kaspper.teste.annotation.FieldEntity;
import com.kaspper.teste.model.type.JobTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JobRequestDTO {

    @FieldEntity(isNotString = true)
    @NotNull(message = "id cannot be null", groups = UpdateEntityGroup.class)
    private Long id;

    private String description;

    @FieldEntity(isNotString = true)
    private JobTypeEnum type;

    @FieldEntity(isNotString = true)
    private Double salary;
}

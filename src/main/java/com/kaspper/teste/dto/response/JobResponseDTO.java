package com.kaspper.teste.dto.response;

import com.kaspper.teste.model.type.JobTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class JobResponseDTO {

    private Long id;

    private String description;

    private JobTypeEnum type;

    private Double salary;
}

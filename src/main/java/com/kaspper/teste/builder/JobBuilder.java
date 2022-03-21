package com.kaspper.teste.builder;

import com.kaspper.teste.dto.request.JobRequestDTO;
import com.kaspper.teste.dto.response.JobResponseDTO;
import com.kaspper.teste.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobBuilder {

    public Job build(JobRequestDTO dto){
        return Job.builder()
                .description(dto.getDescription())
                .id(dto.getId())
                .salary(dto.getSalary())
                .type(dto.getType())
                .build();
    }

    public JobResponseDTO buildResponse(Job job){
        return JobResponseDTO.builder()
                .description(job.getDescription())
                .id(job.getId())
                .salary(job.getSalary())
                .type(job.getType())
                .build();
    }

}

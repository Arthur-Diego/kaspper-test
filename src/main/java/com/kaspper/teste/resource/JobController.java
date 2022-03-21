package com.kaspper.teste.resource;


import com.kaspper.teste.dto.PageDTO;
import com.kaspper.teste.dto.request.CandidateRequestDTO;
import com.kaspper.teste.dto.request.JobRequestDTO;
import com.kaspper.teste.dto.request.UpdateEntityGroup;
import com.kaspper.teste.dto.response.CandidateResponseDTO;
import com.kaspper.teste.dto.response.JobResponseDTO;
import com.kaspper.teste.model.Job;
import com.kaspper.teste.service.JobService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/job")
public class JobController {

    private final JobService jobService;

    @ApiOperation(value = "Find job by id")
    @GetMapping(path = "/find/{id}")
    public ResponseEntity<JobResponseDTO> findAllWithFilters(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(jobService.findById(id));
    }

    @ApiOperation(value = "Find all jobs")
    @GetMapping(path = "/find")
    public ResponseEntity<List<JobResponseDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @ApiOperation(value = "Find all job with filteer")
    @GetMapping(path = "/find/filter")
    public ResponseEntity<List<JobResponseDTO>> findAllWithFilter(JobRequestDTO dto) {
        return ResponseEntity.ok(jobService.findFilter(dto));
    }

    @ApiOperation(value = "Find  all job with Pagination")
    @GetMapping(path = "/find/pageable")
    public ResponseEntity<PageDTO<JobResponseDTO>> findPageable(Pageable pageable) {
        return ResponseEntity.ok(jobService.findPageable(pageable));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create job")
    @PostMapping(path = "/create")
    public void create(@RequestBody @Valid JobRequestDTO dto) {
        jobService.save(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update job")
    @PutMapping(path = "/update")
    public void update(@RequestBody @Validated(UpdateEntityGroup.class) JobRequestDTO dto) {
        jobService.save(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete job")
    @DeleteMapping(path = "/remove/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        jobService.remove(id);
    }
}

package com.kaspper.teste.resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaspper.teste.dto.PageDTO;
import com.kaspper.teste.dto.request.CandidateRequestDTO;
import com.kaspper.teste.dto.request.UpdateEntityGroup;
import com.kaspper.teste.dto.response.CandidateResponseDTO;
import com.kaspper.teste.service.CandidateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/candidate")
public class CandidateController extends AbstractCommonController {

    private final CandidateService candidateService;

    @ApiOperation(value = "Find by id")
    @GetMapping(path = "/find/{id}")
    public ResponseEntity<CandidateResponseDTO> findAllWithFilters(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(candidateService.findBydId(id));
    }

    @ApiOperation(value = "Find all candidate with filters")
    @GetMapping(path = "/find")
    public ResponseEntity<List<CandidateResponseDTO>> findAll() {
        return ResponseEntity.ok(candidateService.findAll());
    }

    @ApiOperation(value = "Find all candidate")
    @GetMapping(path = "/find/filter")
    public ResponseEntity<List<CandidateResponseDTO>> findAllWithFilter(CandidateRequestDTO dto) {
        return ResponseEntity.ok(candidateService.findFilter(dto));
    }

    @ApiOperation(value = "Find with all candidate Pagination")
    @GetMapping(path = "/find/pageable")
    public ResponseEntity<PageDTO<CandidateResponseDTO>> findPageable(Pageable pageable) {
        return ResponseEntity.ok(candidateService.findPageable(pageable));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create candidate")
    @PostMapping(path = "/create")
    public void create(@RequestBody @Valid CandidateRequestDTO dto) {
        candidateService.save(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update candidate")
    @PutMapping(path = "/update")
    public void update(@RequestBody @Validated(UpdateEntityGroup.class) CandidateRequestDTO dto) {
        candidateService.save(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update partial candidate")
    @PatchMapping(path = "/update/{id}")
    public void updatePartial(@PathVariable("id") Long id, @RequestBody CandidateRequestDTO dto) {

        candidateService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete candidate")
    @DeleteMapping(path = "/remove/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        candidateService.remove(id);
    }

    @ApiOperation(value = "Generate curriculum")
    @GetMapping(path = "/curriculum/{id}")
    public void generateReport(@PathVariable(value = "id") Long id, HttpServletResponse response) throws Exception {

        CandidateResponseDTO candidate = candidateService.findBydId(id);
        String encodingBytes = new String(candidate.getCurriculum());
        byte[] data = Base64.getDecoder().decode(encodingBytes);
        generateFile(response, data, "curriculum.pdf");
    }

}

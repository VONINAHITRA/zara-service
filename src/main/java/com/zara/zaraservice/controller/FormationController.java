package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.FormationDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.service.FormationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("formations")
public class FormationController {

    private final FormationService formationService;

    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FormationDTO>> getFormationById(@PathVariable Long id){
        return ResponseEntity.ok(formationService.getFormationById(id));
    }

    @GetMapping("/consultantID/{id}")
    public ResponseEntity<List<FormationDTO>>getFormationsByConsultantId(@PathVariable Long id){
        return ResponseEntity.ok(formationService.getFormationsByConsultantId(id));
    }

    @PostMapping
    public ResponseEntity<FormationDTO> createFormation(@RequestBody FormationDTO formationDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(formationService.createFormation(formationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormationDTO> updateFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO){
        return ResponseEntity.status(HttpStatus.OK).body(formationService.updateFormation(id,formationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFormation(@PathVariable Long id){
        return ResponseEntity.ok(formationService.deleteFormation(id));
    }
}

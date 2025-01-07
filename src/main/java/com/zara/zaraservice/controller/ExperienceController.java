package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.ExperienceDTO;
import com.zara.zaraservice.dto.UserDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ExperienceDTO>>getExperienceById(@PathVariable Long id){
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }

    @GetMapping("/consultantID/{id}")
    public ResponseEntity<List<ExperienceDTO>>getExperiencesByConsultantId(@PathVariable Long id){
        return ResponseEntity.ok(experienceService.getExperiencesByConsultantId(id));
    }

    @PostMapping
    public ResponseEntity<ExperienceDTO> createExperience(@RequestBody ExperienceDTO experienceDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(experienceService.createExperience(experienceDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceDTO> updateExperience(@PathVariable Long id, @RequestBody ExperienceDTO experienceDTO){
        return ResponseEntity.status(HttpStatus.OK).body(experienceService.updateExperience(id,experienceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteExperience(@PathVariable Long id){
        return ResponseEntity.ok(experienceService.deleteExperience(id));
    }
}

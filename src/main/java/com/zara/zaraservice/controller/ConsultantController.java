package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.ClientDTO;
import com.zara.zaraservice.dto.ConsultantDTO;
import com.zara.zaraservice.dto.UserDTO;
import com.zara.zaraservice.dto.responses.ApiResponse;
import com.zara.zaraservice.service.ConsultantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("consultants")
public class ConsultantController {

    private final ConsultantService consultantService;

    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultantDTO>>getAllConsultant(){
        return ResponseEntity.ok(consultantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ConsultantDTO>>getConsultantById(@PathVariable Long id){
        return ResponseEntity.ok(consultantService.getConsultantById(id));
    }

    @GetMapping("/getConsultantByUserId/{userId}")
    public ResponseEntity<Optional<ConsultantDTO>> getConsultantByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(consultantService.getConsultantByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<ConsultantDTO> createConsultant(@RequestBody ConsultantDTO consultantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(consultantService.createConsultant(consultantDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ConsultantDTO>> searchConsultants(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location) {
        log.info("Recherche de consultants avec keyword={} et location={}", keyword, location);
        List<ConsultantDTO> results = consultantService.searchConsultants(keyword, location);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultantDTO> updateConsultant(@PathVariable Long id,@RequestBody ConsultantDTO consultantDTO){
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.updateConsultant(id, consultantDTO));
    }

    //toDO acctiver ou pas
    @PatchMapping("/{id}/availableOrNot")
    public ResponseEntity<ApiResponse> setAvailableOrNotConsultant(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(consultantService.setAvailableOrNotConsultant(id));
    }
}

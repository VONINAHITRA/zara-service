package com.zara.zaraservice.controller;

import com.zara.zaraservice.dto.ClientDTO;
import com.zara.zaraservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>>getAllClients(){
      return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClientDTO>> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getUserById(id));
    }

    @GetMapping("/getClientByUserId/{userId}")
    public ResponseEntity<Optional<ClientDTO>> getClientByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(clientService.getClientByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO>updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.updateClient(id, clientDTO));
    }
}

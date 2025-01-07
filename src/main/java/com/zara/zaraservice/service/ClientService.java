package com.zara.zaraservice.service;

import com.zara.zaraservice.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);

    List<ClientDTO> findAll();

    ClientDTO updateClient(Long id, ClientDTO clientDTO);

    Optional<ClientDTO> getUserById(Long id);

    Optional<ClientDTO> getClientByUserId(Long userId);
}

package com.zara.zaraservice.service.impl;

import com.zara.zaraservice.constants.ErrorMessages;
import com.zara.zaraservice.dto.ClientDTO;
import com.zara.zaraservice.entity.ClientEntity;
import com.zara.zaraservice.entity.UserEntity;
import com.zara.zaraservice.exception.customexceptions.NotFoundException;
import com.zara.zaraservice.mapper.ClientMapper;
import com.zara.zaraservice.repository.ClientRepository;
import com.zara.zaraservice.repository.UserRepository;
import com.zara.zaraservice.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientServiceImpl(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDTO> getUserById(Long id) {
        return Optional.ofNullable(clientRepository.findById(id)
                .map(ClientMapper::toDTO)
                .orElseThrow(()-> new NotFoundException(String.format(ErrorMessages.CLIENT_ID_REQUIRED, id))));
    }

    @Override
    public Optional<ClientDTO> getClientByUserId(Long userId) {
       return Optional.ofNullable(clientRepository.findByUserId(userId)
                .map(ClientMapper::toDTO)
                .orElseThrow(()-> new NotFoundException(String.format(ErrorMessages.CLIENT_ID_REQUIRED, userId))));
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {

        UserEntity userEntity = userRepository.findById(clientDTO.getUser().getId())
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.USER_ID_REQUIRED, clientDTO.getUser().getId())));

        ClientEntity clientEntity = ClientMapper.toEntity(clientDTO);
        clientEntity.setUserEntity(userEntity);

        ClientEntity savedClient = clientRepository.save(clientEntity);

        return ClientMapper.toDTO(savedClient);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {

        ClientEntity existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ErrorMessages.CLIENT_ID_REQUIRED, id)));
        existingClient.setLocalisation(clientDTO.getLocalisation());

        return ClientMapper.toDTO(clientRepository.save(existingClient));
    }
}

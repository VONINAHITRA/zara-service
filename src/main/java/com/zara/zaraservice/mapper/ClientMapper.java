package com.zara.zaraservice.mapper;

import com.zara.zaraservice.dto.ClientDTO;
import com.zara.zaraservice.entity.ClientEntity;
import com.zara.zaraservice.entity.InterventionEntity;
import com.zara.zaraservice.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public static ClientDTO toDTO(ClientEntity client) {
        if (client == null) {
            return null;
        }

        return ClientDTO.builder()
                .id(client.getId())
                .localisation(client.getLocalisation())
                .user(UserMapper.toDTO(client.getUserEntity()))
                .interventions(InterventionMapper.toDTOList(client.getInterventionEntities()))
                .createdDate(client.getCreatedDate())
                .lastModifiedDate(client.getLastModifiedDate())
                .build();
    }

    public static ClientEntity toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        return ClientEntity.builder()
                .localisation(clientDTO.getLocalisation())
                .userEntity(UserMapper.toEntity(clientDTO.getUser()))
                .interventionEntities(InterventionMapper.toEntityList(clientDTO.getInterventions()))
                .build();
    }

    public static List<ClientDTO> toDTOList(List<ClientEntity> clients) {
        if (clients == null || clients.isEmpty()) {
            return null;
        }

        return clients.stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<ClientEntity> toEntityList(List<ClientDTO> clientDTOs) {
        if (clientDTOs == null || clientDTOs.isEmpty()) {
            return null;
        }

        return clientDTOs.stream()
                .map(ClientMapper::toEntity)
                .collect(Collectors.toList());
    }

}

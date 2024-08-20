package com.example.cqrsbankingapp.service.client;

import com.example.cqrsbankingapp.domain.exeption.ResourceNotFoundException;
import com.example.cqrsbankingapp.domain.model.Client;
import com.example.cqrsbankingapp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {

    private ClientRepository clientRepository;

    @Override
    public Client getById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public boolean existsByUsername(String username) {
        return clientRepository.existsByUsername(username);
    }
}

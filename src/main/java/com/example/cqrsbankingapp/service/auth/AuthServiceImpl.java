package com.example.cqrsbankingapp.service.auth;

import com.example.cqrsbankingapp.domain.model.Client;
import com.example.cqrsbankingapp.service.client.ClientService;
import com.example.cqrsbankingapp.web.dto.LoginRequestDto;
import com.example.cqrsbankingapp.web.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ClientService clientService;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        return null;
    }

    @Override
    public void register(Client client) {
        if (clientService.existsByUsername(client.getUsername())) {
            client.setPassword(client.getPassword());
            clientService.create(client);
        }

    }
}

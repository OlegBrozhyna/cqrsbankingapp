package com.example.cqrsbankingapp.service.account;

import com.example.cqrsbankingapp.domain.exeption.ResourceNotFoundException;
import com.example.cqrsbankingapp.domain.model.Account;
import com.example.cqrsbankingapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountRepository accountRepository;

    @Override
    public Account getById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}

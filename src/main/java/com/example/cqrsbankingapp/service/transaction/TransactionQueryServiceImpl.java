package com.example.cqrsbankingapp.service.transaction;

import com.example.cqrsbankingapp.domain.exeption.ResourceNotFoundException;
import com.example.cqrsbankingapp.domain.model.Transaction;
import com.example.cqrsbankingapp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionQueryServiceImpl implements TransactionQueryService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction getById(UUID id) {
        return transactionRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}

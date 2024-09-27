package com.example.cqrsbankingapp.service.card;

import com.example.cqrsbankingapp.domain.exeption.ResourceNotFoundException;
import com.example.cqrsbankingapp.domain.model.Card;
import com.example.cqrsbankingapp.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardQueryServiceImpl implements CardQueryService {

    private final CardRepository cardRepository;

    @Override
    public Card getById(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public boolean existsByNumberAndDate(String number, String date) {
        return cardRepository.existsByNumberAndDate(number,date);
    }
}

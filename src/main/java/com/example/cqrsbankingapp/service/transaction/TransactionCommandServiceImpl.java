package com.example.cqrsbankingapp.service.transaction;

import com.example.cqrsbankingapp.domain.model.Card;
import com.example.cqrsbankingapp.domain.model.Transaction;
import com.example.cqrsbankingapp.events.EventService;
import com.example.cqrsbankingapp.events.TransactionCreateEvent;
import com.example.cqrsbankingapp.service.card.CardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionCommandServiceImpl implements TransactionCommandService {

    private final EventService eventService;

    @Override
    public void create(Transaction object) {
        TransactionCreateEvent event = new TransactionCreateEvent(object);
        eventService.create(event);

    }
}

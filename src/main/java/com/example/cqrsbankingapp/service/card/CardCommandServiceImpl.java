package com.example.cqrsbankingapp.service.card;

import com.example.cqrsbankingapp.domain.model.Card;
import com.example.cqrsbankingapp.events.CardCreateEvent;
import com.example.cqrsbankingapp.events.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CardCommandServiceImpl implements CardCommandService {

    private EventService eventService;

    @Override
    public void create(Card object) {
        CardCreateEvent event = new CardCreateEvent();
        eventService.create(event);

    }
}

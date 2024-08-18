package com.example.cqrsbankingapp.service.client;

import com.example.cqrsbankingapp.domain.model.Client;
import com.example.cqrsbankingapp.events.ClientCreateEvent;
import com.example.cqrsbankingapp.events.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientCommandServiceImpl implements ClientCommandService {

    private EventService eventService;

    @Override
    public void create(Client object) {
        ClientCreateEvent event = new ClientCreateEvent(object);
        eventService.create(event);
    }
}

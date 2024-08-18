package com.example.cqrsbankingapp.events;

import com.example.cqrsbankingapp.domain.aggregate.Aggregate;
import com.example.cqrsbankingapp.domain.model.Client;

public class ClientCreateEvent extends AbstractEvent {

    public ClientCreateEvent(Client payload) {
        super(null, EventType.CLIENT_CREATE, payload);
    }

    @Override
    public void apply(Aggregate aggregate) {

    }
}

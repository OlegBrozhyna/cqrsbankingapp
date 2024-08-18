package com.example.cqrsbankingapp.service.account;

import com.example.cqrsbankingapp.domain.model.Account;
import com.example.cqrsbankingapp.events.EventService;
import com.example.cqrsbankingapp.service.event.AccountCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommandServiceImpl implements AccountCommandService {
    private EventService eventService;

    @Override
    public void create(Account object) {
        AccountCreateEvent event = new AccountCreateEvent(object);
        eventService.create(event);

    }
}

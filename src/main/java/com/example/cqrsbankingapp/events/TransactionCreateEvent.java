package com.example.cqrsbankingapp.events;

import com.example.cqrsbankingapp.domain.aggregate.Aggregate;
import com.example.cqrsbankingapp.domain.model.Transaction;

public class TransactionCreateEvent extends AbstractEvent{

    public TransactionCreateEvent(Transaction payload) {
        super(null, EventType.TRANSACTION_CREATE, payload);
    }

    @Override
    public void apply(Aggregate aggregate) {

    }
}

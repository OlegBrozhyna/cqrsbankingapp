package com.example.cqrsbankingapp.service.transaction;

import com.example.cqrsbankingapp.domain.model.Transaction;
import com.example.cqrsbankingapp.service.CommandService;
import com.example.cqrsbankingapp.service.QueryService;

public interface TransactionService extends QueryService<Transaction>, CommandService<Transaction> {
}

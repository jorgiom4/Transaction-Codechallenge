package com.spring.service.transactioncodechallenge.service;

import com.spring.service.transactioncodechallenge.model.dto.SearchTransactionParams;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntityRequest;

import java.util.List;

/**
 * Interface para el servicio de Transacción
 */
public interface ITransactionService {

    // Persiste una nueva trasacción
    public TransactionEntity save(TransactionEntityRequest transactionEntityRequest);

    // Lista todas las transacciones
    public List<TransactionEntity> findAll();

    // Busca transacciones por número de cuenta IBAN
    public List<TransactionEntity> findTransactions(SearchTransactionParams params);




}

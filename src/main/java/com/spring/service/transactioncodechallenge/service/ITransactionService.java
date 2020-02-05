package com.spring.service.transactioncodechallenge.service;

import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;

import java.util.List;

/**
 * Interface para el servicio de Transacción
 */
public interface ITransactionService {

    // Lista todas las transacciones
    public List<TransactionEntity> findAll();




}

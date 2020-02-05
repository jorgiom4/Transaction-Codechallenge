package com.spring.service.transactioncodechallenge.service;

import com.spring.service.transactioncodechallenge.model.dao.ITransactionDao;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntityRequest;
import com.spring.service.transactioncodechallenge.utils.ValidationRequestBodyParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementación del servicio de Transacción
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    // Trazas en consola
    private static Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private ITransactionDao transactionDao;

    //Utilidades
    private ValidationRequestBodyParam validation = new ValidationRequestBodyParam();

    @Override
    public TransactionEntity save(TransactionEntityRequest transactionEntityRequest) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionEntity> findAll() {

        return (List<TransactionEntity>) transactionDao.findAll();
    }

}

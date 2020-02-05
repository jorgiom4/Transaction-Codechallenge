package com.spring.service.transactioncodechallenge.model.dto;

import com.spring.service.transactioncodechallenge.model.dao.ITransactionDao;
import com.spring.service.transactioncodechallenge.service.ITransactionService;
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


    @Override
    @Transactional(readOnly = true)
    public List<TransactionEntity> findAll() {

        return (List<TransactionEntity>) transactionDao.findAll();
    }

}

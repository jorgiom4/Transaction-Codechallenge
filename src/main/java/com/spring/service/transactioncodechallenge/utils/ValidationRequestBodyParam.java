package com.spring.service.transactioncodechallenge.utils;

import com.spring.service.transactioncodechallenge.exceptions.AccountBalanceCeroException;
import com.spring.service.transactioncodechallenge.exceptions.BadParamsNewTransactionException;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import com.spring.service.transactioncodechallenge.service.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase auxiliar para la validación de los parámetros de entrada
 */
public class ValidationRequestBodyParam {

    // Trazas en consola
    private static Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    /**
     * Valida los parámetros de entrada recibidos en el RequestBody para guardar una nueva transacción
     * @param trans
     * @return boolean
     */
    public boolean validateTransactionRequestBodyParams(TransactionEntity trans){

        // Comprobamos si tenemos la cuenta IBAN
        if(trans.getAccount_iban() == null || trans.getAccount_iban().equals("")){
            throw new BadParamsNewTransactionException();
        }

        // Comprobamos que el importe sea mayor a 0
        if(trans.getAmount() == 0){
            throw new BadParamsNewTransactionException();
        }

        // Comprobamos si el balance total de la transaccion es mayor a 0
        if(trans.getAmount() - trans.getFee() <= 0){
            log.info("Account balance must be greater than 0");
            throw new AccountBalanceCeroException();
        }
        return true;
    }
}

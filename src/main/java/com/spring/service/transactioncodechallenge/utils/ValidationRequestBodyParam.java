package com.spring.service.transactioncodechallenge.utils;

import com.spring.service.transactioncodechallenge.exceptions.AccountBalanceCeroException;
import com.spring.service.transactioncodechallenge.exceptions.BadParamsNewTransactionException;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntityRequest;
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
     * @param  request
     * @return boolean
     */
    public boolean validateTransactionRequestBodyParams(TransactionEntityRequest request){

        // Comprobamos si tenemos la cuenta IBAN
        if(request.getAccount_iban() == null || request.getAccount_iban().equals("")){
            throw new BadParamsNewTransactionException();
        }

        // Comprobamos que el importe sea mayor a 0
        if(request.getAmount() == 0){
            throw new BadParamsNewTransactionException();
        }

        // Comprobamos si el balance total de la transaccion es mayor a 0
        if(request.getAmount() - request.getFee() <= 0){
            log.info("Account balance must be greater than 0");
            throw new AccountBalanceCeroException();
        }
        return true;
    }
}

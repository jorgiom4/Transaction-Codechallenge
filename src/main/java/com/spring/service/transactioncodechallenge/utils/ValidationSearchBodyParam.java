package com.spring.service.transactioncodechallenge.utils;

import com.spring.service.transactioncodechallenge.exceptions.BadParamsSearchTransactionException;
import com.spring.service.transactioncodechallenge.model.dto.SearchTransactionParams;
import com.spring.service.transactioncodechallenge.service.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase auxiliar para la validación de los parámetros de entrada para las búsquedas
 */
public class ValidationSearchBodyParam {

    // Trazas en consola
    private static Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    /**
     * Valida si los parámetros de entrada son correctos para realizar la búsqueda requerida
     * @param searchTransactionParams
     * @return
     */
    public boolean validateSearchParams(SearchTransactionParams searchTransactionParams){

        // Comprobamos si hay parámetros
        if((searchTransactionParams.getType().equals("") || searchTransactionParams.getType() == null) &&
                (searchTransactionParams.getIban().equals("") || searchTransactionParams.getIban() == null) &&
                (searchTransactionParams.getOrder().equals("") || searchTransactionParams.getOrder() == null)){

            throw new BadParamsSearchTransactionException();
        }

        // Comprobamos si el tipo de búsqueda es correcta iban/amount
        if(searchTransactionParams.getType().equals("") || searchTransactionParams.getType() == null) {
            throw new BadParamsSearchTransactionException();
        }
        if(!searchTransactionParams.getType().equals("iban") && !searchTransactionParams.getType().equals("amount")){
            throw new BadParamsSearchTransactionException();
        }

        // Comprobamos si el tipo de búsqueda es por cuenta IBAN que tenga el parámetro
        if(searchTransactionParams.getType().equals("iban")){
            if(searchTransactionParams.getIban().equals("") || searchTransactionParams.getIban() == null){
                throw new BadParamsSearchTransactionException();
            }
        }

        // Comprobamos si el tipo de busqueda en por importe que tenga el orden
        if(searchTransactionParams.getType().equals("amount")){
            if(searchTransactionParams.getOrder().equals("") || searchTransactionParams.getOrder() == null){
                throw new BadParamsSearchTransactionException();
            }
            if(!searchTransactionParams.getOrder().equals("asc") && !searchTransactionParams.getOrder().equals("desc")){
                throw new BadParamsSearchTransactionException();
            }
        }
        return true;
    }
}

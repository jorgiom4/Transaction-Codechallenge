package com.spring.service.transactioncodechallenge.utils;

import com.spring.service.transactioncodechallenge.exceptions.BadParamsSearchTransactionException;
import com.spring.service.transactioncodechallenge.model.dto.StatusTransactionParams;
import com.spring.service.transactioncodechallenge.service.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase auxiliar para la validación de los parámetros de entrada para la consulta de estado de una transacción
 */
public class ValidationStatusRequestBodyParam {

    // Trazas en consola
    private static Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    /**
     * Valida los parámetros de entrada recibidos en el RequestBody para consultar el estado de una transacción
     * @param params
     * @return
     */
    public boolean checkStatusRequestBodyParams(StatusTransactionParams params){

        // Comprobamos que tenemos el parámetro referencia válido
        if(params.getReference().equals("") || params.getReference() == null){
            throw new BadParamsSearchTransactionException();
        }

        /*
        Comprobamos que el parámetro channel tenga uno de los valores posibles correcto
        NOTA: Este campo es opcional, por tanto puede vernir vacio, nulo, no informado
        */
        if(!params.getChannel().equals("")){
            if(!params.getChannel().equals("CLIENT") && !params.getChannel().equals("ATM") && !params.getChannel().equals("INTERNAL")){
                throw new BadParamsSearchTransactionException();
            }
        }

        return true;

    }
}

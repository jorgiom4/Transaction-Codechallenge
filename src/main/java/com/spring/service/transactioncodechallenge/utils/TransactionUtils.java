package com.spring.service.transactioncodechallenge.utils;

import com.spring.service.transactioncodechallenge.model.dto.StatusTransactionParams;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntityResponse;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Componente de apoyo y utilidades que son reutilizables en la aplicación
 */
public class TransactionUtils {

    /**
     * Obtiene la fecha actual en formato timestamp
     * @return String
     */
    public String getTransactionTimeStamp(){
        Instant instant = Instant.now();

        Timestamp timestamp = Timestamp.from(instant);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String timestampAsString = formatter.format(timestamp.toLocalDateTime());
        // System.out.println("Timestamp actual: " + timestampAsString);

        return timestampAsString;
    }

    /**
     * Genera una nueva referencia de transaccion pero no comprueba si ya existe en base de datos
     * La longitud de la referencia viene definida en el fichero de propiedades
     * @Param Integer
     * @return String
     */
    public String generateNewTransactionReference(Integer refSize){

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(refSize);

        for (int i = 0; i < refSize; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();

    }

    /**
     * Obtiene el estado de una transacción dependiendo de los parámetros recibidos
     * @param trans TransactionEntity
     * @param params StatusTransactionParams
     * @return TransactionEntityResponse
     */
    public TransactionEntityResponse getTransactionStatus(TransactionEntity trans, StatusTransactionParams params){
        TransactionEntityResponse response = new TransactionEntityResponse();

        boolean fechaTransIsBefore = false;
        boolean fechaTransIsEquals = false;
        boolean fechaTransIsAfter = false;

        /*
        Business Rule A
        Comprobamos si la referencia de la transacción se ha encontrado en la base de datos, en caso de no existir
        el estato de la transacción será INVALID
        */
        if(trans == null){
            response.setReference(params.getReference());
            response.setStatus("INVALID");
            //BeanUtils.copyProperties(trans, response, "amount", "fee");

        }

        /*
        Business Rule B, C, D, E, F, G, H
        NOTA: En caso de no tener fecha la transacción de tomará como fecha anterior a la actual
        */
        // Obtenemos la fecha actual del sistema
        Instant instant = Instant.now();
        Timestamp timestampNow = Timestamp.from(instant);

        // Comprobamos que la transaccion tenga fecha
        if(!trans.getDate().equals("")){
            //Obtenemos la fecha de la transaccion
            Timestamp timestampTrans = Timestamp.from(Instant.parse(trans.getDate()));

            //Comprobamos la fecha de la transacción respecto a la actual
            if(timestampTrans.equals(timestampNow)){
                fechaTransIsEquals = true;
            }
            if(timestampTrans.before(timestampNow)){
                fechaTransIsBefore = true;
            }
            if(timestampTrans.after(timestampNow)){
                fechaTransIsAfter = true;
            }

        }else{
            // La transacción no tiene fecha por tanto
            fechaTransIsBefore = true;
        }

        // Una vez determinada la fecha de la transaccion comprobamos el canal y actualizamos según las reglas de negocio
        // Business Rule B
        if(params.getChannel().equals("CLIENT") || params.getChannel().equals("ATM") && fechaTransIsBefore){
            response.setReference(trans.getReference());
            response.setStatus("SETTLED");
            response.setAmount(trans.getAmount() - trans.getFee());
        }

        // Business Rule C
        if(params.getChannel().equals("INTERNAL") && fechaTransIsBefore){
            response.setReference(trans.getReference());
            response.setStatus("SETTLED");
            response.setAmount(trans.getAmount());
            response.setFee(trans.getFee());
        }

        // Business Rule D
        if(params.getChannel().equals("CLIENT") || params.getChannel().equals("ATM") && fechaTransIsEquals){
            response.setReference(trans.getReference());
            response.setStatus("PENDING");
            response.setAmount(trans.getAmount() - trans.getFee());
        }

        // Business rule E
        if(params.getChannel().equals("INTERNAL") && fechaTransIsEquals){
            response.setReference(trans.getReference());
            response.setStatus("PENDING");
            response.setAmount(trans.getAmount());
            response.setFee(trans.getFee());
        }

        // Business Rule F
        if(params.getChannel().equals("CLIENT") && fechaTransIsAfter){
            response.setReference(trans.getReference());
            response.setStatus("FUTURE");
            response.setAmount(trans.getAmount() - trans.getFee());
        }

        // Business Rule G
        if(params.getChannel().equals("ATM") && fechaTransIsAfter){
            response.setReference(trans.getReference());
            response.setStatus("PENDING");
            response.setAmount(trans.getAmount() - trans.getFee());
        }


        // Business Rule H
        if(params.getChannel().equals("INTERNAL") && fechaTransIsAfter){
            response.setReference(trans.getReference());
            response.setStatus("FUTURE");
            response.setAmount(trans.getAmount());
            response.setFee(trans.getFee());
        }

        return response;

    }

}

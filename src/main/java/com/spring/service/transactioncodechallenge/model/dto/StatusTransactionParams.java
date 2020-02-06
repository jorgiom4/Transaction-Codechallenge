package com.spring.service.transactioncodechallenge.model.dto;

/**
 * DTO
 * Clase para el mapeo de los parámetros de entrada para el estado de las transacciones
 */
public class StatusTransactionParams {

    private String reference; // Referencia de la transacción
    private String channel; // Canal de la transacción puede ser CLIENT, ATM, INTERNAL

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}

package com.spring.service.transactioncodechallenge.model.dto;

/**
 * DTO
 * Clase para el mapeo de los parámetros de búsqueda de transacciones
 */
public class SearchTransactionParams {

    // Tipo de búsqueda por cuenta IBAN por por cantidad
    private String type;

    // Referencia de la transaccióm
    private String iban;

    // Tipo de orden, acendente o descendente
    private String order;

    //Getters & Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String transactionReference) {
        this.iban = transactionReference;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

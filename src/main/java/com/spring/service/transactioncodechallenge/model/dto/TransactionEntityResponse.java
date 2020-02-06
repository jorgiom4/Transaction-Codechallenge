package com.spring.service.transactioncodechallenge.model.dto;

import java.io.Serializable;

/**
 * DTO
 * Clase Transacción para la devolución de la información de estado de la transacción.
 */
public class TransactionEntityResponse implements Serializable {

    private static final long serialVersionUID = 994567890132347989L;

    private String reference; // Referencia de la transación
    private String status; // Estado de la transacción
    private Double amount; // Importe
    private Double fee; // comisión

    // Constructor por defecto
    public TransactionEntityResponse(){}

    // Constructor con parámetros
    public TransactionEntityResponse(String reference, String status, Double amount, Double fee) {
        this.reference = reference;
        this.status = status;
        this.amount = amount;
        this.fee = fee;
    }

    //Getters & Setters
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}

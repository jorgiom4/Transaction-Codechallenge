package com.spring.service.transactioncodechallenge.model.dto;

import java.io.Serializable;

/**
 * DTO Objeto Transaccion que nos llegará por Payload (RequestBody)
 */
public class TransactionEntityRequest implements Serializable {

    private static final long serialVersionUID = 1234567890134567892L;

    private Long id;

    private String reference;

    private String accountIban;

    private String date;

    private Double amount;

    private Double fee;

    private String description;

    public TransactionEntityRequest() {}

    // Constructor con parámetros
    public TransactionEntityRequest(String reference, String account_iban, String date, Double amount, Double fee, String description) {
        this.reference = reference;
        this.accountIban = account_iban;
        this.date = date;
        this.amount = amount;
        this.fee = fee;
        this.description = description;
    }

    @Override
    public String toString(){
        return "id: " + this.id +
                ", reference: " + this.reference +
                ", account_iban: " + this.accountIban +
                ", amount: " + this.amount +
                ", fee: " + this.fee +
                ", description: " + this.description;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAccount_iban() {
        return accountIban;
    }

    public void setAccount_iban(String account_iban) {
        this.accountIban = account_iban;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

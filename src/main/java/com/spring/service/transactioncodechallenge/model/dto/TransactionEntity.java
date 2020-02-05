package com.spring.service.transactioncodechallenge.model.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad transaccion
 */
@Entity(name = "TransactionEntity")
@Table(name = "transaction")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1234567890134567892L;

    // Identificador único de la referencia de la transaccion, es obligatoria pero si no viene hay que generar una
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    // Identificador de la cuenta IBAN, campo obligatorio
    @Column(name = "accountIban")
    private String accountIban;

    // Fecha de realización de la transacción, campo opcional
    @Column(name = "date")
    private String date;

    // Cantidad de la transacción, campo obligatorio
    @Column(name = "amount")
    private Double amount;

    // Fee de la transacción, campo opcional
    @Column(name = "fee")
    private Double fee;

    // Descripción de la transacción, campo opcional
    @Column(name = "description")
    private String description;

    // Contructor por defecto
    public TransactionEntity() {}

    // Constructor con parámetros
    public TransactionEntity(String reference, String account_iban, String date, Double amount, Double fee, String description) {
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

package com.spring.service.transactioncodechallenge.exceptions;

public class BadParamsSearchTransactionException extends RuntimeException {
    public BadParamsSearchTransactionException(){
        super("Bad params in Search Transaction");
    }
}

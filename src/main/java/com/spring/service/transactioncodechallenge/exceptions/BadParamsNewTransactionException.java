package com.spring.service.transactioncodechallenge.exceptions;

public class BadParamsNewTransactionException extends RuntimeException {
    public BadParamsNewTransactionException(){
        super("Bad params in new Transaction");
    }
}

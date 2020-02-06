package com.spring.service.transactioncodechallenge.exceptions;

public class DuplicateTransactionReferenceException extends RuntimeException {
    public DuplicateTransactionReferenceException(){
        super("Transaction reference must be unique");
    }
}

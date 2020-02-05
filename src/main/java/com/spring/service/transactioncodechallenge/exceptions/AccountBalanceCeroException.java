package com.spring.service.transactioncodechallenge.exceptions;

public class AccountBalanceCeroException extends RuntimeException {
    public AccountBalanceCeroException(){
        super("Account balance must be greater than 0");
    }
}

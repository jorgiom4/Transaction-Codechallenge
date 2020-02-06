package com.spring.service.transactioncodechallenge.controller;

import com.spring.service.transactioncodechallenge.model.dto.SearchTransactionParams;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntityRequest;
import com.spring.service.transactioncodechallenge.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/listar")
    public List<TransactionEntity> findAll(){
        return transactionService.findAll();
    }

    @PostMapping("/new")
    public TransactionEntity save (@RequestBody TransactionEntityRequest request){

        return transactionService.save(request);
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionEntity> find(@RequestBody SearchTransactionParams params){

        return transactionService.findTransactions(params);
    }
}

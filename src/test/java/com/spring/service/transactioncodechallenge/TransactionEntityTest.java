package com.spring.service.transactioncodechallenge;

import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionEntityTest {

    @Autowired
    TransactionEntity transactionEntity;

    @Test
    void saveNewTransactionTest(){

    }

}

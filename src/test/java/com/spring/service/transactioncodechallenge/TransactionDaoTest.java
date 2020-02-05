package com.spring.service.transactioncodechallenge;

import com.spring.service.transactioncodechallenge.model.dao.ITransactionDao;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionDaoTest {

    @Autowired
    private ITransactionDao transactionDao;

    private TransactionEntity transactionEntity;

    @Before
    public void setUp(){
        transactionEntity = new TransactionEntity("11111Q",
                "ES12121212121234567890",
                "2019-09-02T13:59:59.000Z",
                12.50,
                1.2,
                "Descripcion");
    }

    @Test
    public void saveTransactionEntityFindByReference(){
        transactionEntity = transactionDao.save(transactionEntity);
        assertThat(transactionDao.findByReference("1234")).isNull();
    }
}

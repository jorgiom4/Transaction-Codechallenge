package com.spring.service.transactioncodechallenge;

import com.spring.service.transactioncodechallenge.model.dao.ITransactionDao;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private TransactionEntity trans;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup(){
        // Iniciamos los datos de una nueva transacción
        trans = new TransactionEntity(
                "11111Q",
                "ES12121212121234567890",
                "2019-09-02T13:59:59.000Z",
                12.50,
                1.2,
                "Descripcion");
    }

    @Test
    /**
     * Test unitario de inserción nueva transaccion con toda la información
     */
    public void saveNewTransactionTest(){
        TransactionEntity transactionEntitySaved = this.entityManager.persistAndFlush(trans);
        assertThat(transactionEntitySaved.getReference()).isEqualTo(trans.getReference());
        assertThat(transactionEntitySaved.getAccount_iban()).isEqualTo(trans.getAccount_iban());
        assertThat(transactionEntitySaved.getDate()).isEqualTo(trans.getDate());
        assertThat(transactionEntitySaved.getAmount()).isEqualTo(trans.getAmount());
        assertThat(transactionEntitySaved.getFee()).isEqualTo(trans.getFee());
        assertThat(transactionEntitySaved.getDescription()).isEqualTo(trans.getDescription());
    }

}

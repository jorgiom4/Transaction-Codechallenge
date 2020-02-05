package com.spring.service.transactioncodechallenge;

import com.spring.service.transactioncodechallenge.exceptions.BadParamsNewTransactionException;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntityRequest;
import com.spring.service.transactioncodechallenge.utils.ValidationRequestBodyParam;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class validateTransactionRequestBodyParamsTest {

    private TransactionEntityRequest request;

    private ValidationRequestBodyParam validate;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp(){
        request = new TransactionEntityRequest(
                "12121212",
                "ES1234565678865432323",
                "2019-08-04T12:59:59.000Z",
                1239.4,
                123.4,
                "Esto es una descripción");
        validate = new ValidationRequestBodyParam();
    }

    // Validamos que se lanza la excepcion a falta del parámetro account_iban
    @Test()
    public void validateRequestBodyParamsWithNoAccountIbanParameter(){
        request.setAccount_iban("");
        thrown.expect(BadParamsNewTransactionException.class);
        validate.validateTransactionRequestBodyParams(request);
    }

    // Validamos que se lanza la excepcion a falta del parámetro account_iban
    @Test()
    public void validateRequestBodyParamsWithNoAmountParameter(){
        request.setAccount_iban("ES1234565678865432323");
        request.setAmount(0.0);

        thrown.expect(BadParamsNewTransactionException.class);
        validate.validateTransactionRequestBodyParams(request);

    }
}

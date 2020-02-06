package com.spring.service.transactioncodechallenge.service;

import com.spring.service.transactioncodechallenge.exceptions.DuplicateTransactionReferenceException;
import com.spring.service.transactioncodechallenge.model.dao.ITransactionDao;
import com.spring.service.transactioncodechallenge.model.dto.SearchTransactionParams;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import com.spring.service.transactioncodechallenge.model.dto.TransactionEntityRequest;
import com.spring.service.transactioncodechallenge.utils.TransactionUtils;
import com.spring.service.transactioncodechallenge.utils.ValidationRequestBodyParam;
import com.spring.service.transactioncodechallenge.utils.ValidationSearchBodyParam;
import com.spring.service.transactioncodechallenge.utils.ValidationStatusRequestBodyParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de Transacción
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    // Trazas en consola
    private static Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private ITransactionDao transactionDao;

    //Utilidades
    private TransactionUtils utils = new TransactionUtils();
    private ValidationRequestBodyParam validation = new ValidationRequestBodyParam();
    private ValidationSearchBodyParam validationSearch = new ValidationSearchBodyParam();
    private ValidationStatusRequestBodyParam validationStatus = new ValidationStatusRequestBodyParam();

    @Value("${reference.size}")
    private String referenceSize;

    @Override
    public TransactionEntity save(TransactionEntityRequest transactionEntityRequest) {

        boolean hasRef;
        TransactionEntity newTransactionEntity = null;

        // Si los parámetros de entrada son correctos
        if(validation.validateTransactionRequestBodyParams(transactionEntityRequest)){

            //Comprobamos si tenemos la referencia de la transaccion
            hasRef = transactionEntityRequest.getReference() != null && !transactionEntityRequest.getReference().equals("");

            log.info("**** TransactionServiceImpl **** ");
            log.info("hasRef: " + hasRef);

            if(!hasRef){
                log.info("No tenemos referencia de la transaccion, creamos una nueva");
                boolean ref = false;
                do {
                    String newRef = utils.generateNewTransactionReference(Integer.valueOf(referenceSize));
                    TransactionEntity trans = transactionDao.findByReference(newRef);

                    // Si no hay una transacción con esa referencia la podemos usar
                    if(trans == null){
                        String fechaActual = utils.getTransactionTimeStamp();
                        newTransactionEntity = new TransactionEntity(
                                newRef,
                                transactionEntityRequest.getAccount_iban(),
                                transactionEntityRequest.getDate(),
                                transactionEntityRequest.getAmount(),
                                transactionEntityRequest.getFee(),
                                transactionEntityRequest.getDescription());
                        ref = true;
                    }
                }while(!ref);
            }else{
                // Comprobamos si la referencia de la transacción ya existe en el sistema
                TransactionEntity trans = transactionDao.findByReference(transactionEntityRequest.getReference());
                if(trans == null){
                    // Si no existe podemos persistir
                    newTransactionEntity = new TransactionEntity(
                            transactionEntityRequest.getReference(),
                            transactionEntityRequest.getAccount_iban(),
                            transactionEntityRequest.getDate(),
                            transactionEntityRequest.getAmount(),
                            transactionEntityRequest.getFee(),
                            transactionEntityRequest.getDescription());

                }else{
                    throw new DuplicateTransactionReferenceException();
                }
            }
        }
        return transactionDao.save(newTransactionEntity);
    }

    @Override
    @Transactional
    public List<TransactionEntity> findTransactions(SearchTransactionParams params) {

        List<TransactionEntity> transactionEntityList = new ArrayList<>();

        // Comprobamos si los parámetros recibidos son correctos
        if(validationSearch.validateSearchParams(params)){

            if(params.getType().equals("iban")){
                transactionEntityList = transactionDao.findByAccountIban(params.getIban());
            }
            if(params.getType().equals("amount")){
                // Dependiendo del orden requerido
                if(params.getOrder().equals("asc")){
                    transactionEntityList = transactionDao.findByOrderByAmountAsc();
                }
                if(params.getOrder().equals("desc")){
                    transactionEntityList = transactionDao.findByOrderByAmountDesc();
                }
            }
        }

        return transactionEntityList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionEntity> findAll() {

        return (List<TransactionEntity>) transactionDao.findAll();
    }


}

package com.spring.service.transactioncodechallenge.model.dao;

import com.spring.service.transactioncodechallenge.model.dto.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface de acceso a datos
 */
@Repository
public interface ITransactionDao extends CrudRepository<TransactionEntity, Long> {

    public List<TransactionEntity> findAll();


}

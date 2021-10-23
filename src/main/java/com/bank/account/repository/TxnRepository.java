package com.bank.account.repository;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.account.domain.TransactionEntity;

/**
*
* @author Yogya Hewavidana
*
*/

/**
 *
 * If search criteria includes multiple search parameters other than account number ,
 * can use Jpa Specifications or QueryDsl Predicates.
 *
 */
@Repository
public interface TxnRepository extends AbstractRepository<TransactionEntity> {

	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value ="true") })
	@Query(value = "select txn from TransactionEntity txn left join fetch txn.fromAccount af left join fetch txn.toAccount at where af.acctNumber = :accountNumber OR at.acctNumber = :accountNumber", countQuery = "select count(txn) from TransactionEntity txn")
	Page<TransactionEntity> getTransactions(@Param("accountNumber") String accountNumber, Pageable pageable);

}

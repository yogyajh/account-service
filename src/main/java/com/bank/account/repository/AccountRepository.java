package com.bank.account.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bank.account.domain.AccountEntity;

/**
*
* @author Yogya Hewavidana
*
*/

@Repository
public interface AccountRepository extends AbstractRepository<AccountEntity> {

	Optional<AccountEntity> findByAcctNumber(String acctNumber);

}

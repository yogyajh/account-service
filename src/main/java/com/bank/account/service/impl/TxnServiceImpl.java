package com.bank.account.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account.constant.TxnType;
import com.bank.account.domain.AccountEntity;
import com.bank.account.domain.TransactionEntity;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.TxnRepository;
import com.bank.account.resource.TxnDetailResource;
import com.bank.account.service.TxnService;

/**
 *
 * @author Yogya Hewavidana
 *
 */

@Component
public class TxnServiceImpl implements TxnService {

	private TxnRepository txnRepository;

	private AccountRepository accountRepository;

	private ModelMapper mapper;

	@Autowired
	public TxnServiceImpl(TxnRepository txnRepository, AccountRepository accountRepository, ModelMapper mapper) {

		this.txnRepository = txnRepository;
		this.accountRepository = accountRepository;
		this.mapper = mapper;

	}

	
	/**
	 * Hibernate query cache is enabled. If use additional parameters other than
	 * database retrieve values enable @Cacheable to enable spring cache too.
	 * 
	 * @Cacheable(value="simpleCache", key = "'simpleCache'+#accountNumber")
	 */
	@Transactional(readOnly = true)
	public Page<TxnDetailResource> getTxnDetails(String accountNumber, Pageable pageable) {

		AccountEntity userAcct = accountRepository.findByAcctNumber(accountNumber).orElseThrow(
				() -> new EntityNotFoundException("Account not found for accountNumber: " + accountNumber));

		Page<TransactionEntity> acctTransactions = txnRepository.getTransactions(accountNumber, pageable);

		/**
		 * Define model mapper to map TransactionEntity to TxnDetailResource.
		 */
		mapper.typeMap(TransactionEntity.class, TxnDetailResource.class).addMappings(mapper -> {
			mapper.map(src -> src.getCreatedDate(), TxnDetailResource::setDate);

		});

		List<TxnDetailResource> creditTransactions = acctTransactions.getContent().stream()
				.filter(txn -> txn.getToAccount().getAcctNumber().equals(accountNumber))
				.map(txn -> mapper.map(txn, TxnDetailResource.class)).collect(Collectors.toList());

		List<TxnDetailResource> debitTransactions = acctTransactions.getContent().stream()
				.filter(txn -> txn.getFromAccount().getAcctNumber().equals(accountNumber))
				.map(txn -> mapper.map(txn, TxnDetailResource.class)).collect(Collectors.toList());

		creditTransactions.forEach(t -> {
			t.setAcctNumber(userAcct.getAcctNumber());
			t.setAcctName(userAcct.getAcctName());
			t.setCurrency(userAcct.getCurrency());
			t.setTxnType(TxnType.CREDIT);

		});

		debitTransactions.forEach(t -> {
			t.setAcctNumber(userAcct.getAcctNumber());
			t.setAcctName(userAcct.getAcctName());
			t.setCurrency(userAcct.getCurrency());
			t.setTxnType(TxnType.DEBIT);
		});

		;

		return new PageImpl<TxnDetailResource>(Stream.of(creditTransactions, debitTransactions)
				.flatMap(Collection::stream).collect(Collectors.toList()), pageable,
				acctTransactions.getTotalElements());
	}

}

package com.bank.account.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;

import com.bank.account.config.JpaDataConfig;
import com.bank.account.domain.TransactionEntity;

@ContextConfiguration(classes = JpaDataConfig.class)
@DataJpaTest
public class TxnRepoTest {

	@Autowired
	TxnRepository txnRepository;

	public static final String acctNumber = "567459872";

	@Test
	public void findUser() throws Exception {
		Page<TransactionEntity> page = txnRepository.getTransactions(acctNumber,
				PageRequest.of(0, 10, Sort.by("createdDate").descending()));
		assertEquals(4, page.getNumberOfElements());
		assertEquals(acctNumber, page.getContent().iterator().next().getToAccount().getAcctNumber());
	}

}

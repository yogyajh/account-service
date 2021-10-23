
package com.bank.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bank.account.domain.AccountEntity;
import com.bank.account.domain.TransactionEntity;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.TxnRepository;
import com.bank.account.resource.TxnDetailResource;
import com.bank.account.service.impl.TxnServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TxnServiceTest {

	@Mock
	private TxnRepository txnRepository;

	@Mock
	private AccountRepository acctRepository;

	@Mock
	private ModelMapper mapper;

	/**
	 * Disable @InjectMocks to use ModelMapper
	 * 
	 * @Autowired
	 * @InjectMocks 
	 * private TxnServiceImpl txnService;
	 */

	private TxnService txnService;

	public static final String acctNumber = "567459872";

	@BeforeEach
	public void setUp() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		txnService = new TxnServiceImpl(txnRepository, acctRepository, modelMapper);
	}

	@Test
	public void getTxnDetails() {

		AccountEntity acct1 = new AccountEntity();
		acct1.setAcctNumber(acctNumber);
		acct1.setAcctName("Savings account");

		AccountEntity acct2 = new AccountEntity();
		acct2.setAcctNumber("123459872");
		acct2.setAcctName("Savings account");

		TransactionEntity txn = new TransactionEntity();
		txn.setFromAccount(acct1);
		txn.setToAccount(acct2);
		txn.setAmount(new BigDecimal(100.00));

		when(acctRepository.findByAcctNumber(acctNumber)).thenReturn(Optional.of(acct1));
		Pageable pageable = PageRequest.of(0, 10);

		when(txnRepository.getTransactions(acctNumber, pageable))
				.thenReturn(new PageImpl<>(Stream.of(txn).collect(Collectors.toList())));

		Page<TxnDetailResource> page = txnService.getTxnDetails(acctNumber, pageable);

		verify(acctRepository, times(1)).findByAcctNumber(acctNumber);
		assertEquals(1, page.getNumberOfElements());
		assertEquals(acctNumber, page.getContent().iterator().next().getAcctNumber());

	}

	
	  @Test 
	  public void accountNotFound() {
	  
	  assertThrows(EntityNotFoundException.class, () -> {
	  txnService.getTxnDetails(acctNumber, PageRequest.of(0, 10)); });
	  
	  }
	 
}

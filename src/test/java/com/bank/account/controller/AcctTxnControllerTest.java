
package com.bank.account.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.account.config.AccountServiceApplication;
import com.bank.account.constant.Currency;
import com.bank.account.constant.TxnType;
import com.bank.account.resource.TxnDetailResource;
import com.bank.account.service.TxnService;

@SpringBootTest(classes = AccountServiceApplication.class)
@AutoConfigureMockMvc
public class AcctTxnControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TxnService txnService;

	@Test
	public void getAcctTxnDetailsSuccess() throws Exception {

		TxnDetailResource txnDetail1 = new TxnDetailResource("567459872", "Saving Account", Currency.AUD,
				TxnType.CREDIT, new BigDecimal(10000.00), "Credit Transaction", new Date());
		TxnDetailResource txnDetail2 = new TxnDetailResource("145459872", "Current Account", Currency.SGD,
				TxnType.DEBIT, new BigDecimal(2000.00), "Dedit Transaction", new Date());

		when(txnService.getTxnDetails("567459872", PageRequest.of(0, 10, Sort.by("createdDate").descending())))
				.thenReturn(new PageImpl<TxnDetailResource>(
						Stream.of(txnDetail1, txnDetail2).collect(Collectors.toList())));
		this.mockMvc
				.perform(get("/v1/account/txnDetails").queryParam("acctNumber", "567459872").queryParam("page", "0")
						.queryParam("pageSize", "10"))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.content[*].acctNumber", containsInAnyOrder("567459872", "145459872")))
				.andExpect(jsonPath("$.content[*].acctName", containsInAnyOrder("Saving Account", "Current Account")))
				.andExpect(jsonPath("$.content[*].currency", containsInAnyOrder("AUD", "SGD")))
				.andExpect(jsonPath("$.content[*].txnType", containsInAnyOrder("CREDIT", "DEBIT")));

	}

	@Test
	public void getAcctTxnDetailsInvalidURL() throws Exception {

		TxnDetailResource txnDetail1 = new TxnDetailResource("567459872", "Saving Account", Currency.AUD,
				TxnType.CREDIT, new BigDecimal(10000.00), "Credit Transaction", new Date());
		TxnDetailResource txnDetail2 = new TxnDetailResource("145459872", "Current Account", Currency.SGD,
				TxnType.DEBIT, new BigDecimal(2000.00), "Dedit Transaction", new Date());

		when(txnService.getTxnDetails("567459872", PageRequest.of(0, 10)))
				.thenReturn(new PageImpl<>(Stream.of(txnDetail1, txnDetail2).collect(Collectors.toList())));
		this.mockMvc.perform(get("/v1/account/invalidURL").queryParam("acctNumber", "1"))
				.andExpect(status().isNotFound());

	}

	@Test
	public void getAcctTxnDetailsSuccessInvalidReqParams() throws Exception {

		TxnDetailResource txnDetail1 = new TxnDetailResource("567459872", "Saving Account", Currency.AUD,
				TxnType.CREDIT, new BigDecimal(10000.00), "Credit Transaction", new Date());
		TxnDetailResource txnDetail2 = new TxnDetailResource("145459872", "Current Account", Currency.SGD,
				TxnType.DEBIT, new BigDecimal(2000.00), "Dedit Transaction", new Date());

		when(txnService.getTxnDetails("567459872", PageRequest.of(0, 10)))
				.thenReturn(new PageImpl<>(Stream.of(txnDetail1, txnDetail2).collect(Collectors.toList())));
		this.mockMvc.perform(get("/v1/account/txnDetails").queryParam("invalidParameter", "invalidParameter"))
				.andExpect(status().isBadRequest()).andDo(print());

	}

}

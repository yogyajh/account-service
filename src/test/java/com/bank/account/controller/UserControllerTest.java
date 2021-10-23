
package com.bank.account.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bank.account.config.AccountServiceApplication;
import com.bank.account.constant.AcctType;
import com.bank.account.constant.Currency;
import com.bank.account.resource.UserAcctResource;
import com.bank.account.service.UserService;

@SpringBootTest(classes = AccountServiceApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void getUserAccountsSuccess() throws Exception {

		UserAcctResource userAcct1 = new UserAcctResource("567459872", "Saving Account", AcctType.SAVINGS, Currency.AUD,
				new BigDecimal(10000.00), LocalDate.now());
		UserAcctResource userAcct2 = new UserAcctResource("145459872", "Current Account", AcctType.CURRENT,
				Currency.SGD, new BigDecimal(10000.00), LocalDate.now());

		when(userService.getUserAccounts(1L)).thenReturn(Lists.newArrayList(userAcct1, userAcct2));
		this.mockMvc.perform(get("/v1/user/accounts").queryParam("userId", "1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[*].acctNumber", containsInAnyOrder("567459872", "145459872")))
				.andExpect(jsonPath("$[*].acctName", containsInAnyOrder("Saving Account", "Current Account")))
				.andExpect(jsonPath("$[*].acctType", containsInAnyOrder("SAVINGS", "CURRENT")))
				.andExpect(jsonPath("$[*].currency", containsInAnyOrder("AUD", "SGD")));
		;

	}

	@Test
	public void getUserAccountsInvalidURL() throws Exception {

		UserAcctResource userAcct1 = new UserAcctResource("567459872", "Saving Account", AcctType.SAVINGS, Currency.AUD,
				new BigDecimal(10000.00), LocalDate.now());
		UserAcctResource userAcct2 = new UserAcctResource("145459872", "Current Account", AcctType.CURRENT,
				Currency.SGD, new BigDecimal(10000.00), LocalDate.now());

		when(userService.getUserAccounts(1L)).thenReturn(Lists.newArrayList(userAcct1, userAcct2));
		this.mockMvc.perform(get("/v1/user/invalidURL").queryParam("userId", "1")).andExpect(status().isNotFound());

	}

	@Test
	public void getUserAccountsInvalidReqParams() throws Exception {

		UserAcctResource userAcct1 = new UserAcctResource("567459872", "Saving Account", AcctType.SAVINGS, Currency.AUD,
				new BigDecimal(10000.00), LocalDate.now());
		UserAcctResource userAcct2 = new UserAcctResource("145459872", "Current Account", AcctType.CURRENT,
				Currency.SGD, new BigDecimal(10000.00), LocalDate.now());

		when(userService.getUserAccounts(1L)).thenReturn(Lists.newArrayList(userAcct1, userAcct2));
		this.mockMvc.perform(get("/v1/user/accounts").queryParam("invalidParameter", "invalidParameter"))
				.andExpect(status().isBadRequest()).andDo(print());

	}

}

package com.bank.account.resource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.bank.account.constant.AcctType;
import com.bank.account.constant.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
* @author Yogya Hewavidana
*
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAcctResource implements Serializable {

	private static final long serialVersionUID = -233222400596800287L;

	private String acctNumber;

	private String acctName;

	private AcctType acctType;

	private Currency currency;

	private BigDecimal availableBalance;

	private LocalDate date = LocalDate.now();

}

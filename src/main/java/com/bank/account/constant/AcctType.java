package com.bank.account.constant;

import lombok.Getter;
/**
*
* @author Yogya Hewavidana
*
*/

@Getter
public enum AcctType {

	SAVINGS("Savings"), CURRENT("Current"), FD("Fixed Deposit");

	private String name;

	private AcctType(String name) {
		this.name = name;
	}

}

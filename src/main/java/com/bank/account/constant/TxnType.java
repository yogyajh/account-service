package com.bank.account.constant;

import lombok.Getter;

/**
*
* @author Yogya Hewavidana
*
*/

@Getter
public enum TxnType {

	DEBIT("Debit"), CREDIT("Credit");

	private String name;

	private TxnType(String name) {
		this.name = name;
	}

}

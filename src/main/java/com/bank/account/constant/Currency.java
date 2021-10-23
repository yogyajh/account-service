package com.bank.account.constant;

import lombok.Getter;

/**
*
* @author Yogya Hewavidana
*
*/

@Getter
public enum Currency {

	SGD("Singapore dollar"), AUD("Australian Dollar");

	private String name;

	private Currency(String name) {
		this.name = name;
	}

}

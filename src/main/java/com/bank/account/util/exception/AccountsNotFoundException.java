package com.bank.account.util.exception;
/**
*
* @author Yogya Hewavidana
*
*/
public class AccountsNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1163750084880626587L;

	public AccountsNotFoundException(String message) {
		super(message);
	}
}

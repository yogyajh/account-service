package com.bank.account.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
*
* @author Yogya Hewavidana
*
*/
@Getter
@AllArgsConstructor
public class ErrorMessage {

	private int code;
	private String message;

	public ErrorMessage(String message) {
		this.message = message;
	}

}

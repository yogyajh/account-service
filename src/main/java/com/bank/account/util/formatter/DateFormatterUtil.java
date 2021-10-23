package com.bank.account.util.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
*
* @author Yogya Hewavidana
*
*/
public class DateFormatterUtil {

	public static String dateOnly(Date date) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}

}

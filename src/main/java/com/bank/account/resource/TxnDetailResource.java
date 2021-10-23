package com.bank.account.resource;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.bank.account.constant.Currency;
import com.bank.account.constant.TxnType;
import com.bank.account.util.formatter.DateFormatterUtil;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
*
* @author Yogya Hewavidana
*
*/

@Getter
@Setter
@NoArgsConstructor
public class TxnDetailResource implements Serializable {

	private static final long serialVersionUID = 4755928440524959532L;

	private String acctNumber;

	private String acctName;

	private Currency currency;

	private TxnType txnType;

	private BigDecimal amount;

	private String txnNarrative = "";

	private Date date;
   
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private String formattedDate;
    
    
    public TxnDetailResource(String acctNumber, String acctName, Currency currency, TxnType txnType, BigDecimal amount, String txnNarrative, Date date) {
    	this.acctNumber = acctNumber;
    	this.acctName = acctName;
    	this.currency = currency;
    	this.txnType = txnType;
    	this.amount = amount;
    	this.txnNarrative = txnNarrative;
    	this.date = date;
    }
    
    public String getFormattedDate() {
    	return DateFormatterUtil.dateOnly(this.date);
    }

}

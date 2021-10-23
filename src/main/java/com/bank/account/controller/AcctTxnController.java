package com.bank.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.resource.TxnDetailResource;
import com.bank.account.service.TxnService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
/**
*
* @author Yogya Hewavidana
*
*/
@RestController
@RequestMapping("/v1/account")
@Api(tags = "Account Transactions Controller")
@Slf4j
public class AcctTxnController {

	@Autowired
	private TxnService txnService;

	
	@GetMapping(value = "/txnDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<TxnDetailResource>> accountTransactions(@RequestParam("acctNumber") String acctNumber,
			@RequestParam(name = "page", defaultValue = "0", required  = false) Integer page,
			@RequestParam(name = "pageSize", defaultValue = "20", required  = false) Integer pageSize) {

		log.info("Retrieving transaction details for the account: " + acctNumber);

		return ResponseEntity.ok(txnService.getTxnDetails(acctNumber,
				PageRequest.of(page, pageSize, Sort.by("createdDate").descending())));
	}

}

package com.bank.account.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bank.account.resource.TxnDetailResource;
/**
*
* @author Yogya Hewavidana
*
*
 * In real application service methods to perform CRUD operations for
 * transaction entity will contain in this class.
 * 
 */
@Service
public interface TxnService {

	Page<TxnDetailResource> getTxnDetails(String accountNumber, Pageable pageable);

}

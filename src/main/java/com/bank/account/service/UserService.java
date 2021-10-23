package com.bank.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.account.resource.UserAcctResource;
/**
*
* @author Yogya Hewavidana
*
*
 * In real application service methods to perform CRUD operations for user entity will contain in this class.
 * 
 */
@Service
public interface UserService {
	
	List<UserAcctResource> getUserAccounts(Long userId);


}

package com.bank.account.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account.domain.AccountEntity;
import com.bank.account.domain.UserEntity;
import com.bank.account.repository.UserRepository;
import com.bank.account.resource.UserAcctResource;
import com.bank.account.service.UserService;
import com.bank.account.util.exception.AccountsNotFoundException;

/**
 *
 * @author Yogya Hewavidana
 *
 */

@Component
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	ModelMapper mapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {

		this.userRepository = userRepository;
		this.mapper = mapper;

	}

	/**
	 * Hibernate query cache is enabled. If use additional parameters other than
	 * database retrieve values enable @Cacheable to enable spring cache too.
	 * 
	 * @Cacheable(value="simpleCache", key = "'simpleCache'+#userId")
	 */
	@Transactional(readOnly = true)
	public List<UserAcctResource> getUserAccounts(Long userId) {

		/**
		 * In real application userId will retrieve from oauth token this exception
		 * won't occur.
		 */
		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found for userId:" + userId));

		if (user.getUserAccts().isEmpty()) {
			throw new AccountsNotFoundException("No accounts found for the userId: " + userId);
		}

		/**
		 * Define model mapper to map UserAccountEntity to UserAcctResource.
		 */
		mapper.typeMap(AccountEntity.class, UserAcctResource.class).addMappings(mapper -> {
			mapper.map(src -> src.getAcctNumber(), UserAcctResource::setAcctNumber);
			mapper.map(src -> src.getAcctName(), UserAcctResource::setAcctName);
			mapper.map(src -> src.getCurrency(), UserAcctResource::setCurrency);
			mapper.map(src -> src.getAcctType(), UserAcctResource::setAcctType);
		});

		return user.getUserAccts().stream().map(userAcct -> mapper.map(userAcct, UserAcctResource.class))
				.collect(Collectors.toList());

	}

}

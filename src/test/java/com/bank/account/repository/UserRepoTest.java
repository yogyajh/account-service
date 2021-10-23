package com.bank.account.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.bank.account.config.JpaDataConfig;
import com.bank.account.domain.UserEntity;


@ContextConfiguration(classes = JpaDataConfig.class)
@DataJpaTest
public class UserRepoTest {

	@Autowired
	UserRepository userRepository;

	public static final Long userId = 1L;

	@Test
	public void findUser() throws Exception {
		UserEntity user = userRepository.findById(userId).get();
		assertNotNull(user);
		assertEquals(userId, user.getId());
	}


	
}

package com.bank.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.bank.account.constant.AcctType;
import com.bank.account.constant.Currency;
import com.bank.account.domain.AccountEntity;
import com.bank.account.domain.UserEntity;
import com.bank.account.repository.UserRepository;
import com.bank.account.resource.UserAcctResource;
import com.bank.account.service.impl.UserServiceImpl;
import com.bank.account.util.exception.AccountsNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private ModelMapper mapper;

	/**
	 * Disable @InjectMocks to use ModelMapper
	 * 
	 * @Autowired
	 * @InjectMocks 
	 * private UserServiceImpl userService;
	 */

	private UserService userService;

	private Optional<UserEntity> userOpt;

	public static final Long userId = 1L;

	@BeforeEach
	public void setUp() {

		userService = new UserServiceImpl(userRepository, new ModelMapper());
		userOpt = getMockUser();
	}

	@Test
	public void getUserAccounts() {

		when(userRepository.findById(userId)).thenReturn(userOpt);

		List<UserAcctResource> userAccts = userService.getUserAccounts(userId);

		verify(userRepository, times(1)).findById(userId);
		assertEquals(1, userAccts.size());
		assertEquals("567459872", userAccts.iterator().next().getAcctNumber());

	}

	@Test
	public void userNotFound() {

		assertThrows(EntityNotFoundException.class, () -> {
			userService.getUserAccounts(userId);
		});

	}

	@Test
	public void AccountsNotFound() {

		when(userRepository.findById(userId)).thenReturn(Optional.of(new UserEntity()));

		assertThrows(AccountsNotFoundException.class, () -> {
			userService.getUserAccounts(userId);
		});

	}

	private Optional<UserEntity> getMockUser() {

		UserEntity user = new UserEntity();
		user.setId(userId);
		user.setName("test user");
		user.setMobileNumber("0112-2334873");
		user.setIdentityNumber("1234");
		user.setPassportNumber("5663322");

		AccountEntity userAcct = new AccountEntity();
		userAcct.setId(3L);
		userAcct.setUser(user);
		userAcct.setAcctNumber("567459872");
		userAcct.setAcctName("Saving Account");
		userAcct.setAcctType(AcctType.SAVINGS);
		userAcct.setCurrency(Currency.AUD);
		userAcct.setAvailableBalance(new BigDecimal(10000.00));
		user.setUserAccts(Sets.newLinkedHashSet(userAcct));

		return Optional.of(user);
	}

}

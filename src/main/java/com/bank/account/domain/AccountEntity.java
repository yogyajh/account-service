package com.bank.account.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;

import com.bank.account.constant.AcctType;
import com.bank.account.constant.Currency;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
*
* @author Yogya Hewavidana
*
*/
@Entity
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(columnNames = { "acct_number" }) })
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AccountEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 9013151503308578258L;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@NotNull
	@Column(name = "acct_number", nullable = false)
	private String acctNumber;

	@NotNull
	@Column(name = "acct_name", nullable = false)
    private String acctName;

	@Enumerated(EnumType.STRING)
	@Column(name = "acct_type")
	private AcctType acctType;

	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private Currency currency;

	@Column(name = "available_balance")
	private BigDecimal availableBalance;

	@Column(name = "total_balance")
	private BigDecimal totalBalance;

}

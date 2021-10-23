package com.bank.account.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
*
* @author Yogya Hewavidana
*
*
 * Assumptions: Single Account can have multiple transactions.(Credit transactions and Debit transactions.)
 * Single transaction has from account and to account.
 * ex: transfer from acc1 to acc2. acc1 -> from account. acc2 -> to account.
 * Same transaction will return when querying by passing acc1 or acc2.
 * For acc1 transaction type will be Debit and for acc2 transaction type will be Credit.
 * 
 * TransactionEntity and AccountEntity association is defined as unidirectional ManytoOne mapping.
 * If needed can make it as bidirectional by defining two list of transactions for credit transactions and debit transactions
 * in the AccountEntity as well.
 * 
 */
@Entity
@Table(name = "transaction")
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5957049044772187578L;

    @NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "from_acct")
	private AccountEntity fromAccount;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "to_acct")
	private AccountEntity toAccount;

    @NotNull
	@Column(name = "amount", nullable = false)
    private BigDecimal amount;
    
    

}

package com.bank.account.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;

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
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "identity_number", "passport_number" }) })
@Cacheable
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5152143155216359030L;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "dob", nullable = false)
	private Date dob;
	
	@NotNull
	@Column(name = "identity_number", nullable = false)
	private String identityNumber;

    @Column(name = "passport_number")
	private String passportNumber;

	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;

    @Column(name = "address")
	private String address;

	@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<AccountEntity> userAccts = new LinkedHashSet<>();
	

}

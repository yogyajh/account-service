package com.bank.account.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name="seq_generator", sequenceName = "base_seq", allocationSize=50)
	private Long id;

	@CreatedDate
	@Column(name = "created_date")
	private Date createdDate;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Date updatedDate;

	@Version
	@Column(name = "version", columnDefinition = " integer DEFAULT 0 ")
	private int version;

	@CreatedBy
	@Column(name = "created_by")
	private Long createdBy;

	@LastModifiedBy
	@Column(name = "updated_by")
	private Long updatedBy;


}

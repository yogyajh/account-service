package com.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
*
* @author Yogya Hewavidana
*
*/
@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>{

    
}

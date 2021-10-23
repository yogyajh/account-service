package com.bank.account.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Yogya Hewavidana
 *
 */

@Configuration
@EntityScan({ "com.bank.account.domain" })
@EnableJpaRepositories({ "com.bank.account.repository" })
//@EnableJpaAuditing
public class JpaDataConfig {

	/**
	 * To enable JpaAuditing retrieve userId from oauth token and return it in the
	 * SpringSecurityAuditorAware getCurrentAuditor()
	 * 
	 * @Bean public AuditorAware<Long> auditorProvider() { 
	 * return new SpringSecurityAuditorAware(); }
	 */
}

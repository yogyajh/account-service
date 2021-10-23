package com.bank.account.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
/**
*
* @author Yogya Hewavidana
*
*/

@Configuration
@EnableCaching(proxyTargetClass=true)
public class CacheConfig extends CachingConfigurerSupport{

    @Bean
    @Lazy()
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setShared(true);
        factory.setCacheManagerName("ehCacheManagerFactoryBean");
        factory.setAcceptExisting(true);
        return factory;
    }

    @Bean
    @Lazy()
    @Override
    public CacheManager cacheManager() {       
        EhCacheCacheManager manager = new EhCacheCacheManager();
        manager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return manager;
    }

}


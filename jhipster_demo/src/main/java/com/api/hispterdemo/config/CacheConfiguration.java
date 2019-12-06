package com.api.hispterdemo.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.api.hispterdemo.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.api.hispterdemo.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.api.hispterdemo.domain.User.class.getName());
            createCache(cm, com.api.hispterdemo.domain.Authority.class.getName());
            createCache(cm, com.api.hispterdemo.domain.User.class.getName() + ".authorities");
            createCache(cm, com.api.hispterdemo.domain.ToChuc.class.getName());
            createCache(cm, com.api.hispterdemo.domain.ToChuc.class.getName() + ".officers");
            createCache(cm, com.api.hispterdemo.domain.Officer.class.getName());
            createCache(cm, com.api.hispterdemo.domain.Officer.class.getName() + ".deTais");
            createCache(cm, com.api.hispterdemo.domain.Officer.class.getName() + ".nhanSuTGS");
            createCache(cm, com.api.hispterdemo.domain.CapDeTai.class.getName());
            createCache(cm, com.api.hispterdemo.domain.CapDeTai.class.getName() + ".deTais");
            createCache(cm, com.api.hispterdemo.domain.TrangThai.class.getName());
            createCache(cm, com.api.hispterdemo.domain.TrangThai.class.getName() + ".deTais");
            createCache(cm, com.api.hispterdemo.domain.XepLoai.class.getName());
            createCache(cm, com.api.hispterdemo.domain.XepLoai.class.getName() + ".deTais");
            createCache(cm, com.api.hispterdemo.domain.DanhGia.class.getName());
            createCache(cm, com.api.hispterdemo.domain.NhanSuTG.class.getName());
            createCache(cm, com.api.hispterdemo.domain.DuToan.class.getName());
            createCache(cm, com.api.hispterdemo.domain.TienDo.class.getName());
            createCache(cm, com.api.hispterdemo.domain.TienDo.class.getName() + ".fileAttaches");
            createCache(cm, com.api.hispterdemo.domain.FileAttach.class.getName());
            createCache(cm, com.api.hispterdemo.domain.DeTai.class.getName());
            createCache(cm, com.api.hispterdemo.domain.DeTai.class.getName() + ".duToans");
            createCache(cm, com.api.hispterdemo.domain.DeTai.class.getName() + ".tienDos");
            createCache(cm, com.api.hispterdemo.domain.DeTai.class.getName() + ".danhGias");
            createCache(cm, com.api.hispterdemo.domain.DeTai.class.getName() + ".fileAttaches");
            createCache(cm, com.api.hispterdemo.domain.DeTai.class.getName() + ".nhanSuTGS");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}

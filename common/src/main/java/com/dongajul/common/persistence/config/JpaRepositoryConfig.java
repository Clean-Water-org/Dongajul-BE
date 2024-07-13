package com.dongajul.common.persistence.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@Configuration
@EnableJpaRepositories(basePackages = "com.dongajul")
public class JpaRepositoryConfig {
    public JpaRepositoryConfig() {
        log.info("JPA REPOSITORY CONFIG");
    }
}

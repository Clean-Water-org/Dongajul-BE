package com.dongajul.user.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.dongajul.user.adapter.out.persistence.jpa")
public class PersistenceJpaConfig {
}

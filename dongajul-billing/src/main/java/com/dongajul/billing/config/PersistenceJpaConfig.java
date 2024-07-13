package com.dongajul.billing.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.dongajul.billing.adapter.out.persistence.jpa")
public class PersistenceJpaConfig {
}

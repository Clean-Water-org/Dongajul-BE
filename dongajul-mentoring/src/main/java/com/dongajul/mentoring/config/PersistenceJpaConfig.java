package com.dongajul.mentoring.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.dongajul.mentoring.adapter.out.persistence.jpa")
public class PersistenceJpaConfig {
}

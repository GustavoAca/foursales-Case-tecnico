package com.foursales.foursale_desafio.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.foursales.foursale_desafio.domain.model")
@EnableJpaRepositories(basePackages = {"com.foursales.foursale_desafio.domain.repository"})
public class RepositoryConfig {
}

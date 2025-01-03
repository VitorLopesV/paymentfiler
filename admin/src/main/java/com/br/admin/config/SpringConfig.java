package com.br.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "com.br")
@EnableMongoRepositories(basePackages = "com.br")
public class SpringConfig {
}

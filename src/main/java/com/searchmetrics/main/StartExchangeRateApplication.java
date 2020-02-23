package com.searchmetrics.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories("com.searchmetrics.repository")
@EntityScan("com.searchmetrics.entity")
@ComponentScan("com.searchmetrics")
@SpringBootApplication
public class StartExchangeRateApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartExchangeRateApplication.class,args);
    }
}

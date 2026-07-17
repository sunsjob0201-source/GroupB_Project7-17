package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "jp.groupb.shop")
@EntityScan("jp.groupb.shop.model")
@EnableJpaRepositories("jp.groupb.shop.repository")
public class GroupBProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(GroupBProjectApplication.class, args);
    }
}

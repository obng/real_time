package com.kiu.real_time;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan(basePackages = "com.kiu.real_time.Evaluation.model")
@EnableJpaRepositories(basePackages = "com.kiu.real_time.Evaluation.repository")

public class RealTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeApplication.class, args);
    }

}

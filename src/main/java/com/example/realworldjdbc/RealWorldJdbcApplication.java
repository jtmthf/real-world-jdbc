package com.example.realworldjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.realworldjdbc.config.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class RealWorldJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealWorldJdbcApplication.class, args);
    }

}

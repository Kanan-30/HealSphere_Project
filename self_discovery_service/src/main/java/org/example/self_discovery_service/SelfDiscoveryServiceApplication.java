package org.example.self_discovery_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SelfDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfDiscoveryServiceApplication.class, args);
    }

}

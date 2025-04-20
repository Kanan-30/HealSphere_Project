package org.example.login_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class loginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(loginServiceApplication.class, args);
    }

}

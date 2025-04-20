package org.example.letters_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class LettersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LettersServiceApplication.class, args);
    }

}

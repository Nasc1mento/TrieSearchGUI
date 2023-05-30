package com.example.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("urls")
    public String[] urls() {
        return new String[] {
                "https://en.wikipedia.org/wiki/Trie",
                "https://portal.ifpe.edu.br/campus/igarassu",
                "https://globo.com",
                "https://amazon.com",
                "https://reddit.com",
        };
    }
}

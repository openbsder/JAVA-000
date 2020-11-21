package com.example.springbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserCache userCache() {
        return new UserCache();
    }
}

package com.example.user.config;

import com.example.domain.config.JwtAuthenticationiProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtAuthenticationiProvider jwtAuthenticationiProvider(){
        return new JwtAuthenticationiProvider();
    }
}

package com.entvy.openbidhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return registry -> registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Vite dev server
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

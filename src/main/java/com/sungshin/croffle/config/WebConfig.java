package com.sungshin.croffle.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://34.64.45.86")
                .allowedMethods("OPTIONS", "HEAD", "GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}

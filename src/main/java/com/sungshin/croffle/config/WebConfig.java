package com.sungshin.croffle.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://34.64.186.163", "http://34.64.173.239")
//                .allowedOrigins("*")
//                .allowedMethods("*")
                .allowedMethods("OPTIONS", "HEAD", "GET", "POST", "DELETE", "PUT")
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type")
                .maxAge(3600);
    }
}

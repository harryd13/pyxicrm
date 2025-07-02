package com.pyxicm.prod.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // You can restrict to "http://localhost:3000" etc.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
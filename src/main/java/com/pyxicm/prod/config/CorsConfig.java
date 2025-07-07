// src/main/java/com/pyxicm/prod/config/CorsConfig.java
package com.pyxicm.prod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // all paths
                .allowedOrigins("http://localhost:5173") // frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // allow all REST
                .allowedHeaders("*") // allow all headers including Authorization
                .allowCredentials(true); // allow cookies/auth headers
    }
}

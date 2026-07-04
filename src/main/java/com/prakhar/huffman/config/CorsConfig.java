/*
    Without CORS, when React (port 5173) tries to call Spring Boot (port 8080), the browser will block it.
    When React sends
    http://localhost:5173
            ↓
    Spring Boot
    http://localhost:8080

    removes blocked by cors policy
    allows frontend to interact with backend
 */

package com.prakhar.huffman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://localhost:5174",
                "https://huffman-coder-web.vercel.app"
        ));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader(HttpHeaders.CONTENT_DISPOSITION);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
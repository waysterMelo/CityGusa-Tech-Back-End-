package com.citygusa.com.citygusaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class CityGusaApiApplication implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://192.168.0.129:3000") // Your React app's URL
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    public static void main(String[] args) {
        SpringApplication.run(CityGusaApiApplication.class, args);
    }

}

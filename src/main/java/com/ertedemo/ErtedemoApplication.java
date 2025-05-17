package com.ertedemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ErtedemoApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ErtedemoApplication.class, args);
    }
    @Configuration
    public static class Myconfiguration{
        @Bean
        public WebMvcConfigurer corsConfigurer(){
            return new WebMvcConfigurer() {
                @Override
                    public void addCorsMappings(CorsRegistry registry) {
                        registry.addMapping("/**");
                    }
            };
        }
    }

}

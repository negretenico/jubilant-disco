package com.jubilant_disco.service.JubliantDisco.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperCfg {
    @Bean
    ObjectMapper simpleObjectMapper() {
        return new ObjectMapper();
    }
}

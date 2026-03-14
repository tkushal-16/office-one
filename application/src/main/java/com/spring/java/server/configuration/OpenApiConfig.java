package com.spring.java.server.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI aiotelOneOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("office_One API")
                        .version("v1")
                        .description("API documentation for Office One"));
    }
}
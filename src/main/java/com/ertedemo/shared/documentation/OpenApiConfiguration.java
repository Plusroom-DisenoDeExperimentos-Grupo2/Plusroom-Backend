package com.ertedemo.shared.documentation;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI learningPlatformOpenApi(){
        return new OpenAPI()
                .info(new Info().title("PlusRoom API")
                        .description(
                                "PlusRoom Platform application REST API documentation")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://springgoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("PlusRoom platform with Documentation")
                        .url("https://acme-learning-platform.wik.github.org/docs"));
    }
}

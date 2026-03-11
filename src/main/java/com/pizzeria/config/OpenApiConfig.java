package com.pizzeria.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pizzeriaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pizzería API")
                        .description("API REST de la Pizzería")
                        .version("1.0"));
    }
}

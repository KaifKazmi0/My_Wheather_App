package com.kaif.wheatherapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                // =========================
                // API INFO
                // =========================
                .info(new Info()
                        .title("Weather App API")
                        .description("""
                                Complete Weather Application API Documentation.
                                
                                Features:
                                - Current Weather
                                - Forecast Weather
                                - Authentication Ready
                                - JWT Security Ready
                                - Swagger Documentation
                                - Redis Cache Ready
                                - Rate Limiting Ready
                                """)
                        .version("1.0.0")

                        .contact(new Contact()
                                .name("Kaif")
                                .email("sayedkaif784@gmail.com")
                                .url("https://github.com/KaifKazmi0"))

                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))

                // =========================
                // SERVERS
                // =========================
                .servers(List.of(

                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development Server"),

                        new Server()
                                .url("https://weather-api-production.com")
                                .description("Production Server")

                ))

                // =========================
                // SECURITY CONFIG
                // =========================
                .addSecurityItem(new SecurityRequirement()
                        .addList(SECURITY_SCHEME_NAME))

                .components(new Components()

                        .addSecuritySchemes(SECURITY_SCHEME_NAME,

                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token")

                        )
                )

                // =========================
                // EXTERNAL DOCS
                // =========================
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://github.com/KaifKazmi0"));
    }
}
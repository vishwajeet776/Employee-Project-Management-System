package com.example.EmployeeManagementSystem.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean // Swagger URL = http://localhost:8080/swagger-ui.html
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Project Management API")
                        .version("1.0")
                        .description("API documentation for Employee Management System")
                )
                .servers(List.of(
                        new Server().url("https://api.provatosoft.com/v1").description("Production"),
                        new Server().url("http://localhost:8080").description("Local Dev")
                ));
    }
}

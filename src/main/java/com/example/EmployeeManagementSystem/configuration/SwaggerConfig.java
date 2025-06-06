package com.example.EmployeeManagementSystem.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.awt.event.WindowFocusListener;

@Configuration
public class SwaggerConfig {

    @Bean                                        //Swagger URl = http://localhost:8080/swagger-ui.html
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Project Management API")
                        .version("1.0")
                        .description("\"API documentation for Employee Management System") );
    }
}

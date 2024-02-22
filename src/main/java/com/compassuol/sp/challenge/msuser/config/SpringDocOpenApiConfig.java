package com.compassuol.sp.challenge.msuser.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI(){

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("security", securityScheme()))
                .info(
                        new Info()
                                .title("User - Microservice")
                                .description("API for user management")
                                .version("v1")
                                .license(new License().name("Apache 2.0").url("https://apache.org/licenses/LICENSE-2.0"))
                                .contact(new Contact().name("Vinícius Guerra").email("itsviniciusguerra@gmail.com"))
                );
    }

    private SecurityScheme securityScheme(){
        return  new SecurityScheme()
                .description("A valid JWT token must be included in the Authorization header of the HTTP request.")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization");
    }
}

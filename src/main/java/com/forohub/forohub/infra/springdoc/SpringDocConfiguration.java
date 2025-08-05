package com.forohub.forohub.infra.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Foro Hub API")
                        .description("API Rest de la aplicación del Foro Hub, que contiene las funcionalidades CRUD de tópicos. "
                                + "La autenticación se realiza mediante cookies (JWT en HttpOnly cookie). "
                                + "Para usar Swagger, asegúrate de haber iniciado sesión antes.")
                        .contact(new Contact()
                                .name("NosakaKing")
                                .email("raulduran2808@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://voll.med/api/licencia")));
    }
}

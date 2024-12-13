package com.newnocturnalhunter.api_rest.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * The type Swagger config.
 */
@Configuration
public class SwaggerConfig { // Clase que configura la documentación de la API REST usando Swagger
    /**
     * Define open api open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("API para un videojuego");

        Contact myContact = new Contact();
        myContact.setName("Joaquin Castro Salas");

        Info information = new Info()
                .title("API NEW NOCTURNAL HUNTERS")
                .version("1.0")
                .description("API para un videojuego")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}

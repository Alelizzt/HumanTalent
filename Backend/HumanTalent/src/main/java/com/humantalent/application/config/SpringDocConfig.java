package com.humantalent.application.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    public static final Contact SUPPORTED_CONTACTS= new Contact()
            .name("Alejandro List")
            .email("alejandrolistc@hotmail.com")
            .url("https://github.com/Alelizzt");
    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("HumanTalent API")
                        .description("API REST/SOAP para la administración de emlpeados")
                        .contact(SUPPORTED_CONTACTS)
                        .version("1.0"));
    }
}

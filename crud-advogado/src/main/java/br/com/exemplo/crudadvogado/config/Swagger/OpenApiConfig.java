package br.com.exemplo.crudadvogado.config.Swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Projeto Lynx",
                description = "API para advogados gerenciar, processos, clientes e agenda",
                contact = @Contact(
                        name = "Lynx",
                        url = "https://github.com/LYNX-Grupo-9"
                ),
                license = @License(name = "UNLICENSED"),
                version = "1.0.0"

        )
)
@SecurityScheme(
                name = "Bearer",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
)
public class OpenApiConfig {
}

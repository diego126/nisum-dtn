package com.nisum.dtn.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "NISUM Technical Exercise",
                description = "API RESTful de creación de usuarios.",
                contact = @Contact(
                        name = "Diego Tarazona Nieto",
                        email = "diego126.tn@gmail.com"
                )
        ),
        servers = {
                @Server(
                        description = "QA Env",
                        url = "https://nisum-dtn.tyncomperu.com/"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfiguration {
}

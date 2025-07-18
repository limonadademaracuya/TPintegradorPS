package ar.edu.centro8.ps.jwt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "fakeCBC - API Educativa", version = "1.0", description = "API protegida con JWT. Aprendí a usar swaggeeeeeeer ¯\\_(ツ)_//¯"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class SwaggerConfig {
 //bueno, básicamente sin las anotaciones de esta
 //clase swagger no reconoce que estamos usando jwt :)

 //para tener a la mano, link para testearlo:
 // http://localhost:8080/swagger-ui/index.html
}

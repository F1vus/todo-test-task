package net.f1v.todotesttask.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TODO service REST API")
                        .version("1.0")
                        .description("Swagger documentation for todo project")
                )
                .addServersItem(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local server")
                );
    }
}

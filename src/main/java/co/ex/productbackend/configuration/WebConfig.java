package co.ex.productbackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir paths específicos o todos con /**.
                .allowedOrigins("http://localhost:5173") // Origenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowCredentials(true)
                .allowedHeaders("*") // Headers permitidos
                .maxAge(3600); // Tiempo máximo que la respuesta de preflight puede ser cacheada
    }
}

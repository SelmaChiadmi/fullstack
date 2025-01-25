package com.Vaccination.projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Pour la CORS policy
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}

	@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Applique à tous les endpoints
                        .allowedOrigins("http://localhost:4200") // Remplacez par l'origine front-end
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Méthodes autorisées
                        .allowedHeaders("*") // Tous les headers sont acceptés
                        .allowCredentials(true); // Pour les cookies si nécessaire
            }
        };
    }
  }

}

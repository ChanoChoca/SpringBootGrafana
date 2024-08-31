package com.chanochoca.app.loans.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * La clase `RestClientConfig` es una clase de configuración en Spring que define los beans
 * necesarios para la configuración de clientes REST utilizando `RestTemplate`.
 *
 * Esta configuración facilita la creación y configuración de un `RestTemplate` que puede ser
 * utilizado para realizar solicitudes HTTP a otros servicios, como un servicio de fraude.
 */
@Configuration
public class RestClientConfig {

    /**
     * Declara un bean de tipo `RestTemplate` configurado para interactuar con el servicio de fraude.
     *
     * @param restTemplateBuilder Un `RestTemplateBuilder` proporcionado por Spring Boot que facilita
     *                            la configuración de instancias de `RestTemplate`.
     * @return Una instancia de `RestTemplate` configurada con la URI base del servicio de fraude.
     *
     * Este `RestTemplate` se configura con la URI base "http://localhost:8081", lo que permite que
     * todas las solicitudes realizadas con esta instancia estén dirigidas a dicho servicio.
     * El `RestTemplate` es utilizado para realizar llamadas HTTP de manera sencilla y eficiente.
     */
    @Bean
    RestTemplate fraudServiceRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .rootUri("http://localhost:8081")  // Establece la URI base para las solicitudes.
                .build();
    }
}

package com.chanochoca.app.loans.client;

import com.chanochoca.app.loans.entity.LoanStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * La clase `FraudDetectionClient` es un cliente REST que se utiliza para interactuar con un servicio de detección de fraude.
 *
 * Esta clase se encarga de realizar una solicitud HTTP al servicio de detección de fraude para evaluar si un cliente
 * está involucrado en actividades fraudulentas, y en función de eso, devuelve un estado de préstamo (`LoanStatus`).
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class FraudDetectionClient {

    private final RestTemplate fraudServiceRestTemplate;

    /**
     * Evalúa el estado de un préstamo para un cliente específico consultando el servicio de detección de fraude.
     *
     * @param customerId El ID del cliente que se va a evaluar.
     * @return El estado del préstamo (`LoanStatus`), que puede ser `APPROVED` o `REJECTED` basado en la respuesta del servicio de fraude.
     *
     * Este método realiza una solicitud GET al endpoint del servicio de fraude utilizando `RestTemplate`.
     * Los detalles del cliente se envían como parámetro de consulta, y el servicio devuelve un estado
     * que indica si el préstamo debe ser aprobado o rechazado.
     */
    public LoanStatus evaluateLoan(int customerId) {
        log.info("Calling Fraud Detection Service for customer id: {}", customerId);
        var response = fraudServiceRestTemplate.exchange("/fraud/check?customerId=" + customerId,
                        HttpMethod.GET, null, LoanStatus.class)
                .getBody();
        log.info("Fraud Detection Service response: {}", response);
        return response;
    }

}

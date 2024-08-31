package com.chanochoca.app.fraudetect.controller;

import com.chanochoca.app.fraudetect.entity.LoanStatus;
import com.chanochoca.app.fraudetect.service.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * `FraudDetectionController` es un controlador de Spring que maneja las solicitudes relacionadas con la detección de fraude.
 *
 * Este controlador expone un endpoint HTTP para verificar si un cliente tiene un registro de fraude, interactuando con el
 * servicio `FraudDetectionService` para obtener el estado del préstamo basado en la existencia de registros de fraude.
 */
@RestController
@RequestMapping("/fraud")
@RequiredArgsConstructor
@Slf4j
public class FraudDetectionController {

    private final FraudDetectionService fraudDetectionService;

    /**
     * Verifica si existe un registro de fraude para un cliente específico.
     *
     * @param customerId El ID del cliente para verificar si tiene un registro de fraude.
     * @return `LoanStatus.REJECTED` si existe un registro de fraude para el cliente, `LoanStatus.APPROVED` en caso contrario.
     *
     * Este método maneja solicitudes GET en el endpoint `/fraud/check`. Utiliza el servicio `FraudDetectionService` para
     * comprobar si el cliente con el `customerId` proporcionado tiene un registro de fraude. El resultado se devuelve como
     * un estado de préstamo (`LoanStatus`) que indica si el préstamo está aprobado o rechazado basado en la existencia
     * del registro de fraude. El método también registra información sobre la verificación de fraude utilizando `Slf4j`.
     */
    @GetMapping("/check")
    public LoanStatus checkForFraud(@RequestParam int customerId) {
        log.info("Checking for fraud for customer id: {}", customerId);
        return fraudDetectionService.checkForFraud(customerId);
    }
}

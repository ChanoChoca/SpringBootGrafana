package com.chanochoca.app.fraudetect.service;

import com.chanochoca.app.fraudetect.entity.LoanStatus;
import com.chanochoca.app.fraudetect.repository.FraudRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * `FraudDetectionService` es un servicio que proporciona la lógica de negocio para la detección de fraude.
 *
 * Este servicio interactúa con el repositorio de registros de fraude para determinar el estado del préstamo basado en
 * la existencia de registros de fraude asociados con un cliente específico.
 */
@Service
@RequiredArgsConstructor
public class FraudDetectionService {

    private final FraudRecordRepository fraudRecordRepository;

    /**
     * Verifica si existe un registro de fraude para el cliente especificado y devuelve el estado del préstamo
     * basado en la existencia de dicho registro.
     *
     * @param customerId El ID del cliente para verificar si tiene un registro de fraude.
     * @return `LoanStatus.REJECTED` si existe un registro de fraude para el cliente, `LoanStatus.APPROVED` en caso contrario.
     *
     * Este método utiliza el repositorio de registros de fraude (`fraudRecordRepository`) para determinar si hay un registro
     * de fraude para el cliente con el ID proporcionado. Si se encuentra un registro de fraude, el préstamo se considera
     * como rechazado (`LoanStatus.REJECTED`). Si no se encuentra ningún registro de fraude, el préstamo se considera
     * aprobado (`LoanStatus.APPROVED`).
     */
    public LoanStatus checkForFraud(int customerId) {
        return fraudRecordRepository.existsByCustomerId(customerId) ? LoanStatus.REJECTED : LoanStatus.APPROVED;
    }
}

package com.chanochoca.app.fraudetect.entity;

/**
 * `FraudRecord` es un registro de Java que representa un registro de fraude en el sistema.
 *
 * Este registro contiene información sobre un cliente específico y el estado del préstamo relacionado con
 * la detección de fraude. Los registros son una forma compacta y eficiente de agrupar datos inmutables.
 *
 * @param id El ID del registro de fraude en la base de datos.
 * @param fraudRecordId El identificador único del registro de fraude.
 * @param customerId El ID del cliente asociado con el registro de fraude.
 * @param loanStatus El estado del préstamo asociado con el registro de fraude.
 */
public record FraudRecord(Long id, String fraudRecordId, int customerId, LoanStatus loanStatus) {
}

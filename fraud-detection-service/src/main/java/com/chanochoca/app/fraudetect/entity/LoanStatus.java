package com.chanochoca.app.fraudetect.entity;

/**
 * `LoanStatus` es una enumeración que representa los posibles estados de un préstamo en el sistema.
 *
 * Las enumeraciones en Java son una forma de definir un conjunto de constantes relacionadas, que pueden ser usadas
 * para representar estados, categorías u opciones predefinidas de manera clara y segura.
 *
 * En este caso, `LoanStatus` define los dos posibles estados que puede tener un préstamo en el contexto de detección de fraude:
 *
 * - **APPROVED:** El préstamo ha sido aprobado.
 * - **REJECTED:** El préstamo ha sido rechazado.
 */
public enum LoanStatus {
    APPROVED, REJECTED
}

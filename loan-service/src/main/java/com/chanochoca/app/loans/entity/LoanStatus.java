package com.chanochoca.app.loans.entity;

/**
 * La enumeración `LoanStatus` representa los posibles estados de un préstamo.
 *
 * Un préstamo puede estar en uno de los siguientes estados:
 *
 * - `APPROVED`: El préstamo ha sido aprobado.
 * - `REJECTED`: El préstamo ha sido rechazado.
 */
public enum LoanStatus {
    /** Estado que indica que el préstamo ha sido aprobado. */
    APPROVED,

    /** Estado que indica que el préstamo ha sido rechazado. */
    REJECTED
}

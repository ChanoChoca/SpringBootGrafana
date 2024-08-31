package com.chanochoca.app.loans.dto;

import com.chanochoca.app.loans.entity.Loan;
import com.chanochoca.app.loans.entity.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * La clase `LoanDto` es un objeto de transferencia de datos (DTO) que representa la información de un préstamo.
 *
 * Esta clase se utiliza para transferir datos relacionados con un préstamo entre diferentes capas de la aplicación,
 * como la capa de persistencia y la capa de servicio.
 *
 * Se utiliza Lombok para generar automáticamente los métodos getter, setter, constructor y builder pattern.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDto {
    /** Identificador único del préstamo a nivel de negocio. */
    private String loanId;

    /** Nombre del cliente asociado al préstamo. */
    private String customerName;

    /** Identificador único del cliente. */
    private int customerId;

    /** Monto total del préstamo. */
    private BigDecimal amount;

    /** Estado actual del préstamo (por ejemplo, aprobado, rechazado, pendiente). */
    private LoanStatus loanStatus;

    /**
     * Convierte una instancia de `Loan` en un `LoanDto`.
     *
     * @param loan El objeto `Loan` que contiene la información del préstamo.
     * @return Una instancia de `LoanDto` construida a partir del objeto `Loan` proporcionado.
     *
     * Este método facilita la conversión de un objeto `Loan` a `LoanDto` para su uso en transferencias
     * de datos a través de la aplicación.
     */
    public static LoanDto from(Loan loan) {
        return new LoanDto(
                loan.getLoanId(),
                loan.getCustomerName(),
                loan.getCustomerId(),
                loan.getAmount(),
                loan.getLoanStatus()
        );
    }
}

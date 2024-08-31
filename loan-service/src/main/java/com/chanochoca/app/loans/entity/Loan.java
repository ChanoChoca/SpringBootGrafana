package com.chanochoca.app.loans.entity;

import com.chanochoca.app.loans.dto.LoanDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * La clase `Loan` representa un préstamo en el sistema.
 * Esta clase contiene información sobre un préstamo, incluyendo el ID, nombre del cliente,
 * monto del préstamo, y su estado.
 *
 * Se utiliza Lombok para generar automáticamente los métodos getter, setter, constructor,
 * y builder pattern, facilitando la creación y manipulación de objetos `Loan`.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {
    /** Identificador único del préstamo en la base de datos. */
    private Long id;

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
     * Convierte un objeto `LoanDto` en una instancia de `Loan`.
     *
     * @param loanDto El objeto `LoanDto` que contiene la información del préstamo.
     * @return Una instancia de `Loan` construida a partir del `LoanDto` proporcionado.
     *
     * Este método verifica si los campos `loanId` y `loanStatus` en `LoanDto` son nulos.
     * Si ambos son nulos, el método devuelve una instancia de `Loan` sin esos campos,
     * de lo contrario, devuelve una instancia de `Loan` con todos los campos asignados.
     */
    public static Loan from(LoanDto loanDto) {
        if (loanDto.getLoanId() == null && loanDto.getLoanStatus() == null) {
            return Loan.builder()
                    .loanId(loanDto.getLoanId())
                    .amount(loanDto.getAmount())
                    .customerId(loanDto.getCustomerId())
                    .customerName(loanDto.getCustomerName())
                    .build();
        }
        return Loan.builder()
                .loanId(loanDto.getLoanId())
                .amount(loanDto.getAmount())
                .customerId(loanDto.getCustomerId())
                .customerName(loanDto.getCustomerName())
                .build();
    }
}

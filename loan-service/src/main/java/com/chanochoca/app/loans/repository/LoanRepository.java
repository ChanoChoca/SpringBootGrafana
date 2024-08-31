package com.chanochoca.app.loans.repository;

import com.chanochoca.app.loans.entity.Loan;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * La clase `LoanRepository` maneja las operaciones de acceso a datos para la entidad `Loan`.
 *
 * Este repositorio interactúa con la base de datos utilizando `JdbcClient` para realizar
 * consultas y actualizaciones en la tabla de préstamos (`loans`). Se encarga de las operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) relacionadas con los préstamos.
 *
 * Las transacciones están manejadas por las anotaciones `@Transactional`, y la observabilidad
 * del repositorio es mejorada por la anotación `@Observed`.
 */
@Repository
@RequiredArgsConstructor
@Observed
public class LoanRepository {

    /** Cliente JDBC utilizado para interactuar con la base de datos. */
    private final JdbcClient jdbcClient;

    /**
     * Recupera todos los préstamos de la base de datos.
     *
     * @return Una lista de objetos `Loan` que representan todos los préstamos almacenados en la base de datos.
     *
     * Esta consulta es de solo lectura, como se indica con la anotación `@Transactional(readOnly = true)`.
     */
    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        var findQuery = "SELECT id, loanId, customerName, customerId, amount, loanStatus FROM loans";
        return jdbcClient.sql(findQuery).query(Loan.class).list();
    }

    /**
     * Guarda un nuevo préstamo en la base de datos y devuelve su ID generado.
     *
     * @param loan El objeto `Loan` que contiene la información del préstamo a guardar.
     * @return El ID generado del préstamo guardado en la base de datos.
     *
     * Este método inserta un nuevo registro en la tabla `loans`. Utiliza un `KeyHolder` para capturar
     * y devolver el ID generado automáticamente por la base de datos. La operación es transaccional.
     */
    @Transactional
    public Long save(Loan loan) {
        var insertQuery = "INSERT INTO loans(loanId, customerName, customerId, amount, loanStatus) VALUES(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(insertQuery)
                .param(1, UUID.randomUUID().toString())  // Genera un UUID único para el campo `loanId`.
                .param(2, loan.getCustomerName())        // Asigna el nombre del cliente.
                .param(3, loan.getCustomerId())          // Asigna el ID del cliente.
                .param(4, loan.getAmount())              // Asigna el monto del préstamo.
                .param(5, loan.getLoanStatus().toString()) // Asigna el estado del préstamo.
                .update();
        return keyHolder.getKeyAs(Long.class);
    }
}

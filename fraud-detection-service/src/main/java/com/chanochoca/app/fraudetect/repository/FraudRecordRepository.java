package com.chanochoca.app.fraudetect.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * `FraudRecordRepository` es una clase de repositorio que gestiona el acceso a los registros de fraude en la base de datos.
 *
 * Esta clase utiliza `JdbcClient` para ejecutar consultas SQL y realizar operaciones de acceso a datos relacionadas con
 * los registros de fraude. Proporciona un método para verificar si existe un registro de fraude para un cliente específico.
 */
@Repository
@RequiredArgsConstructor
public class FraudRecordRepository {

    private final JdbcClient jdbcClient;

    /**
     * Verifica si existe un registro de fraude para un cliente específico basado en el ID del cliente.
     *
     * @param customerId El ID del cliente para verificar si tiene un registro de fraude.
     * @return `true` si existe al menos un registro de fraude para el cliente, `false` en caso contrario.
     *
     * Este método ejecuta una consulta SQL que cuenta el número de registros de fraude asociados con el `customerId`
     * proporcionado. Si el conteo es mayor que cero, el método devuelve `true`, indicando que existe al menos un registro de fraude
     * para el cliente. De lo contrario, devuelve `false`.
     */
    @Transactional(readOnly = true)
    public boolean existsByCustomerId(int customerId) {
        var sql = """
                SELECT COUNT(*) AS fraud__record_exists
                FROM fraud_records
                WHERE customerId = :customerId;
                """;
        return jdbcClient.sql(sql)
                .param("customerId", customerId)
                .query(Integer.class)
                .single() > 0;
    }
}

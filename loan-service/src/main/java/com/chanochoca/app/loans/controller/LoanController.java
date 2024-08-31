package com.chanochoca.app.loans.controller;

import com.chanochoca.app.loans.dto.LoanDto;
import com.chanochoca.app.loans.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * La clase `LoanController` es un controlador REST en Spring que maneja las solicitudes HTTP
 * relacionadas con los préstamos. Esta clase expone endpoints para listar todos los préstamos
 * y para aplicar a un nuevo préstamo.
 *
 * Este controlador interactúa con el servicio de préstamos (`LoanService`) para realizar las operaciones
 * necesarias y devolver las respuestas adecuadas a las solicitudes entrantes.
 */
@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    /**
     * Endpoint para listar todos los préstamos.
     *
     * @return Una lista de objetos `LoanDto` que representan todos los préstamos existentes.
     *
     * Este método responde a las solicitudes GET en la ruta `/loan`. Llama al método
     * `listAllLoans` del servicio de préstamos para obtener una lista de todos los préstamos
     * y los devuelve con un estado HTTP 200 (OK).
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LoanDto> listAllLoans() {
        return loanService.listAllLoans();
    }

    /**
     * Endpoint para aplicar a un nuevo préstamo.
     *
     * @param loanDto Un objeto `LoanDto` que contiene la información del préstamo solicitado.
     * @return Un mensaje de éxito o fracaso basado en el resultado de la solicitud de préstamo.
     *
     * Este método responde a las solicitudes POST en la ruta `/loan`. Recibe un objeto
     * `LoanDto` en el cuerpo de la solicitud que contiene los detalles del préstamo
     * solicitado. Llama al método `applyLoan` del servicio de préstamos para procesar la solicitud
     * y devuelve un mensaje indicando si la solicitud fue exitosa o no.
     */
    @PostMapping
    public String applyLoan(@RequestBody LoanDto loanDto) {
        return loanService.applyLoan(loanDto);
    }
}

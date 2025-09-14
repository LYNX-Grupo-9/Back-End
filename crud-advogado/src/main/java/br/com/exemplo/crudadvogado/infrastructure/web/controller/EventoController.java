package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.evento.CriarEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.CriarEventoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.evento.CriarEventoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final CriarEventoUseCase criarEventoUseCase;

    public EventoController(CriarEventoUseCase criarEventoUseCase) {
        this.criarEventoUseCase = criarEventoUseCase;
    }

    @PostMapping("/criar")
    @SecurityRequirement(name = "Bearer")
    public CriarEventoResponse criar(@RequestBody CriarEventoCommand command) {
        return criarEventoUseCase.executar(command);
    }

}

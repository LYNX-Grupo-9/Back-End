package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.processo.CriarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.CriarProcessoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.processo.CriarProcessoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    private final CriarProcessoUseCase criarProcessoUseCase;

    public ProcessoController(CriarProcessoUseCase criarProcessoUseCase) {
        this.criarProcessoUseCase = criarProcessoUseCase;
    }

    @PostMapping("/cadastrar")
    @SecurityRequirement(name = "Bearer")
    public CriarProcessoResponse cadastrar(@RequestBody CriarProcessoCommand command) {
        return criarProcessoUseCase.executar(command);
    }

}

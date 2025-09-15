package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.anexo.CriarAnexoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.anexo.CriarAnexoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.anexo.CriarAnexoUseCase;
import br.com.exemplo.crudadvogado.core.domain.Anexo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anexos")
public class AnexoController {

    private final CriarAnexoUseCase  criarAnexoUseCase;

    public AnexoController(CriarAnexoUseCase criarAnexoUseCase) {
        this.criarAnexoUseCase = criarAnexoUseCase;
    }

    @PostMapping("/criar")
    @SecurityRequirement(name = "Bearer")
    public CriarAnexoResponse criarAnexo(@RequestBody CriarAnexoCommand command){
        return criarAnexoUseCase.executar(command);
    }

}

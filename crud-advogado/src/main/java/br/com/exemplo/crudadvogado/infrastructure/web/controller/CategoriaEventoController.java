package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento.CriarCategoriaEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CriarCategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento.CriarCategoriaEventoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias-evento")
public class CategoriaEventoController {

    private final CriarCategoriaEventoUseCase criarCategoriaEventoUseCase;

    public CategoriaEventoController(CriarCategoriaEventoUseCase criarCategoriaEventoUseCase) {
        this.criarCategoriaEventoUseCase = criarCategoriaEventoUseCase;
    }

    @PostMapping("/criar")
    @SecurityRequirement(name = "Bearer")
    public CriarCategoriaEventoResponse cadastrar(@RequestBody CriarCategoriaEventoCommand command) {
        return criarCategoriaEventoUseCase.executar(command);
    }

}

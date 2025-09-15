package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.CriarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.CriarClienteResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.cliente.CriarClienteUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.cliente.ListarClientesPorAdvogadoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final CriarClienteUseCase criarClienteUseCase;
    private final ListarClientesPorAdvogadoUseCase listarClientesPorAdvogadoUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, ListarClientesPorAdvogadoUseCase listarClientesPorAdvogadoUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.listarClientesPorAdvogadoUseCase = listarClientesPorAdvogadoUseCase;
    }

    @PostMapping("/cadastrar")
    @SecurityRequirement(name = "Bearer")
    public CriarClienteResponse cadastrar(@RequestBody CriarClienteCommand command) {
        return criarClienteUseCase.executar(command);
    }

    @GetMapping("/advogado/{advogadoId}")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorAdvogado(@PathVariable UUID advogadoId) {
        return listarClientesPorAdvogadoUseCase.executar(advogadoId);
    }

}

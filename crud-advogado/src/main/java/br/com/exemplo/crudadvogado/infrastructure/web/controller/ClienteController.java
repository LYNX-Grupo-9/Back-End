package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.AtualizarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.CriarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.CriarClienteResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.cliente.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final CriarClienteUseCase criarClienteUseCase;
    private final ListarClientesPorAdvogadoUseCase listarClientesPorAdvogadoUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final ContarClientesPorAdvogadoUseCase contarClientesPorAdvogadoUseCase;
    private final BuscarClientesPorTextoUseCase buscarClientesPorTextoUseCase;
    private final ListarClientesOrdenadosPorProcessosUseCase listarClientesOrdenadosPorProcessosUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, ListarClientesPorAdvogadoUseCase listarClientesPorAdvogadoUseCase, AtualizarClienteUseCase atualizarClienteUseCase, ContarClientesPorAdvogadoUseCase contarClientesPorAdvogadoUseCase, BuscarClientesPorTextoUseCase buscarClientesPorTextoUseCase, ListarClientesOrdenadosPorProcessosUseCase listarClientesOrdenadosPorProcessosUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.listarClientesPorAdvogadoUseCase = listarClientesPorAdvogadoUseCase;
        this.atualizarClienteUseCase = atualizarClienteUseCase;
        this.contarClientesPorAdvogadoUseCase = contarClientesPorAdvogadoUseCase;
        this.buscarClientesPorTextoUseCase = buscarClientesPorTextoUseCase;
        this.listarClientesOrdenadosPorProcessosUseCase = listarClientesOrdenadosPorProcessosUseCase;
    }

    @PostMapping("/cadastrar")
    @SecurityRequirement(name = "Bearer")
    public CriarClienteResponse cadastrar(@RequestBody CriarClienteCommand command) {
        return criarClienteUseCase.executar(command);
    }

    @GetMapping("/advogado/{advogadoId}")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorAdvogado(@PathVariable UUID idAdvogado) {
        return listarClientesPorAdvogadoUseCase.executar(idAdvogado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(
            @PathVariable UUID id,
            @RequestBody AtualizarClienteCommand command) {
        ClienteResponse response = atualizarClienteUseCase.executar(id, command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/advogado/{idAdvogado}/count")
    public ResponseEntity<Long> contarClientesPorAdvogado(@PathVariable UUID idAdvogado) {
        Long count = contarClientesPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponse>> buscarClientesPorTexto(
            @RequestParam String termo,
            @RequestParam UUID idAdvogado) {

        List<ClienteResponse> clientes = buscarClientesPorTextoUseCase.executar(termo, idAdvogado);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-por-processos")
    public ResponseEntity<List<ClienteResponse>> listarOrdenadosPorProcessos(
            @PathVariable UUID idAdvogado) {

        List<ClienteResponse> clientes = listarClientesOrdenadosPorProcessosUseCase.executar(idAdvogado);
        return ResponseEntity.ok(clientes);
    }
}

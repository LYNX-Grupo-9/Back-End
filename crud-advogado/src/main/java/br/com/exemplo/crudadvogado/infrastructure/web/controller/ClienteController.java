package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.AtualizarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.CriarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteProcessoEventoResponse;
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
    private final ListarClientesOrdenadosPorDataNascimentoUseCase listarClientesOrdenadosPorDataNascimentoUseCase;
    private final ListarClientesOrdenadosPorNaturalidadeUseCase listarClientesOrdenadosPorNaturalidadeUseCase;
    private final ListarClientesOrdenadosPorNomeUseCase listarClientesOrdenadosPorNomeUseCase;
    private final ListarTodosClientesUseCase listarTodosClientesUseCase;
    private final BuscarClienteComQuantidadeProcessosUseCase buscarClienteComQuantidadeProcessosUseCase;
    private final BuscarDadosClienteCompletoUseCase buscarDadosClienteCompletoUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, ListarClientesPorAdvogadoUseCase listarClientesPorAdvogadoUseCase, AtualizarClienteUseCase atualizarClienteUseCase, ContarClientesPorAdvogadoUseCase contarClientesPorAdvogadoUseCase, BuscarClientesPorTextoUseCase buscarClientesPorTextoUseCase, ListarClientesOrdenadosPorProcessosUseCase listarClientesOrdenadosPorProcessosUseCase, ListarClientesOrdenadosPorDataNascimentoUseCase listarClientesOrdenadosPorDataNascimentoUseCase, ListarClientesOrdenadosPorNaturalidadeUseCase listarClientesOrdenadosPorNaturalidadeUseCase, ListarClientesOrdenadosPorNomeUseCase listarClientesOrdenadosPorNomeUseCase, ListarTodosClientesUseCase listarTodosClientesUseCase, BuscarClienteComQuantidadeProcessosUseCase buscarClienteComQuantidadeProcessosUseCase, BuscarDadosClienteCompletoUseCase buscarDadosClienteCompletoUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.listarClientesPorAdvogadoUseCase = listarClientesPorAdvogadoUseCase;
        this.atualizarClienteUseCase = atualizarClienteUseCase;
        this.contarClientesPorAdvogadoUseCase = contarClientesPorAdvogadoUseCase;
        this.buscarClientesPorTextoUseCase = buscarClientesPorTextoUseCase;
        this.listarClientesOrdenadosPorProcessosUseCase = listarClientesOrdenadosPorProcessosUseCase;
        this.listarClientesOrdenadosPorDataNascimentoUseCase = listarClientesOrdenadosPorDataNascimentoUseCase;
        this.listarClientesOrdenadosPorNaturalidadeUseCase = listarClientesOrdenadosPorNaturalidadeUseCase;
        this.listarClientesOrdenadosPorNomeUseCase = listarClientesOrdenadosPorNomeUseCase;
        this.listarTodosClientesUseCase = listarTodosClientesUseCase;
        this.buscarClienteComQuantidadeProcessosUseCase = buscarClienteComQuantidadeProcessosUseCase;
        this.buscarDadosClienteCompletoUseCase = buscarDadosClienteCompletoUseCase;
    }

    @PostMapping("/cadastrar")
    @SecurityRequirement(name = "Bearer")
    public CriarClienteResponse cadastrar(@RequestBody CriarClienteCommand command) {
        return criarClienteUseCase.executar(command);
    }

    @GetMapping("/listarPorAdvogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public List<ClienteResponse> listarPorAdvogado(@PathVariable UUID idAdvogado) {
        return listarClientesPorAdvogadoUseCase.executar(idAdvogado);
    }

    @PatchMapping("/{idCliente}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteResponse> atualizarCliente(
            @PathVariable UUID idCliente,
            @RequestBody AtualizarClienteCommand command) {
        ClienteResponse response = atualizarClienteUseCase.executar(idCliente, command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/advogado/{idAdvogado}/total-clientes")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Long> contarClientesPorAdvogado(@PathVariable UUID idAdvogado) {
        Long count = contarClientesPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/buscar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteResponse>> buscarClientesPorTexto(
            @RequestParam String termo,
            @RequestParam UUID idAdvogado) {

        List<ClienteResponse> clientes = buscarClientesPorTextoUseCase.executar(termo, idAdvogado);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-por-processos")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteResponse>> listarOrdenadosPorProcessos(
            @PathVariable UUID idAdvogado) {

        List<ClienteResponse> clientes = listarClientesOrdenadosPorProcessosUseCase.executar(idAdvogado);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-por-data-nascimento")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteResponse>> listarOrdenadosPorDataNascimento(
            @PathVariable UUID idAdvogado) {

        List<ClienteResponse> clientes = listarClientesOrdenadosPorDataNascimentoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-por-naturalidade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteResponse>> listarOrdenadosPorNaturalidade(
            @PathVariable UUID idAdvogado) {

        List<ClienteResponse> clientes = listarClientesOrdenadosPorNaturalidadeUseCase.executar(idAdvogado);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-por-nome")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteResponse>> listarOrdenadosPorNome(
            @PathVariable UUID idAdvogado) {

        List<ClienteResponse> clientes = listarClientesOrdenadosPorNomeUseCase.executar(idAdvogado);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/listar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteResponse>> listarTodos() {
        List<ClienteResponse> clientes = listarTodosClientesUseCase.executar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteResponse> buscarClienteComQuantidadeProcessos(
            @PathVariable UUID id) {

        ClienteResponse cliente = buscarClienteComQuantidadeProcessosUseCase.executar(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{idCliente}/dados-completos")
    @SecurityRequirement(name = "Bearer")
    public ClienteProcessoEventoResponse buscarDadosClienteCompleto(@PathVariable UUID idCliente) {
        return buscarDadosClienteCompletoUseCase.executar(idCliente);
    }
}

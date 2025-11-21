package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.AtualizarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.CriarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteProcessoEventoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.CriarClienteResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.cliente.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ListarClientesPaginadoUseCase listarClientesPaginadoUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, ListarClientesPorAdvogadoUseCase listarClientesPorAdvogadoUseCase, AtualizarClienteUseCase atualizarClienteUseCase, ContarClientesPorAdvogadoUseCase contarClientesPorAdvogadoUseCase, BuscarClientesPorTextoUseCase buscarClientesPorTextoUseCase, ListarClientesOrdenadosPorProcessosUseCase listarClientesOrdenadosPorProcessosUseCase, ListarClientesOrdenadosPorDataNascimentoUseCase listarClientesOrdenadosPorDataNascimentoUseCase, ListarClientesOrdenadosPorNaturalidadeUseCase listarClientesOrdenadosPorNaturalidadeUseCase, ListarClientesOrdenadosPorNomeUseCase listarClientesOrdenadosPorNomeUseCase, ListarTodosClientesUseCase listarTodosClientesUseCase, BuscarClienteComQuantidadeProcessosUseCase buscarClienteComQuantidadeProcessosUseCase, BuscarDadosClienteCompletoUseCase buscarDadosClienteCompletoUseCase, ListarClientesPaginadoUseCase listarClientesPaginadoUseCase) {
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
        this.listarClientesPaginadoUseCase = listarClientesPaginadoUseCase;
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

    @GetMapping("/{id}/dados-completos")
    @SecurityRequirement(name = "Bearer")
    public ClienteProcessoEventoResponse buscarDadosClienteCompleto(@PathVariable UUID id) {
        return buscarDadosClienteCompletoUseCase.executar(id);
    }

    @GetMapping("/paginado")
    @SecurityRequirement(name = "Bearer")
    public Page<ClienteResponse> listarPaginado(Pageable pageable) {
        return listarClientesPaginadoUseCase.executar(pageable);
    }

    @PostMapping("/cache/paginado/limpar")
    @SecurityRequirement(name = "Bearer")
    @CacheEvict(value = "clientesPaginados", allEntries = true)
    public ResponseEntity<String> limparCacheClientesPaginados() {
        System.out.println("🧹 Cache de clientes paginados limpo!");
        return ResponseEntity.ok("Cache de clientes paginados limpo com sucesso!");
    }

    /**
     * Força a atualização do cache para uma página específica
     */
    @PostMapping("/cache/paginado/atualizar")
    @SecurityRequirement(name = "Bearer")
    @CachePut(
            value = "clientesPaginados",
            key = "T(java.util.Objects).hash(#pageable.pageNumber, #pageable.pageSize, #pageable.sort.toString())"
    )
    public Page<ClienteResponse> atualizarCacheClientesPaginados(Pageable pageable) {
        System.out.println("🔄 Atualizando cache forçado para página: " + pageable.getPageNumber());
        return listarClientesPaginadoUseCase.executar(pageable);
    }

    /**
     * Limpa o cache de clientes por advogado (se você tiver cache nesse use case)
     */
    @PostMapping("/cache/por-advogado/limpar")
    @SecurityRequirement(name = "Bearer")
    @CacheEvict(value = "clientesPorAdvogado", allEntries = true)
    public ResponseEntity<String> limparCacheClientesPorAdvogado() {
        System.out.println("🧹 Cache de clientes por advogado limpo!");
        return ResponseEntity.ok("Cache de clientes por advogado limpo com sucesso!");
    }

    /**
     * Limpa TODOS os caches de clientes
     */
    @PostMapping("/cache/limpar-tudo")
    @SecurityRequirement(name = "Bearer")
    @CacheEvict(value = {"clientesPaginados", "clientesPorAdvogado"}, allEntries = true)
    public ResponseEntity<String> limparTodosCachesClientes() {
        System.out.println("🧹🧹🧹 TODOS os caches de clientes limpos!");
        return ResponseEntity.ok("Todos os caches de clientes foram limpos com sucesso!");
    }

    /**
     * Endpoint para verificar status do cache (opcional)
     */
    @GetMapping("/cache/status")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> statusCache() {
        return ResponseEntity.ok("""
            🎯 Sistema de Cache Ativo!
            
            Caches disponíveis:
            - clientesPaginados: Consultas paginadas de clientes
            - clientesPorAdvogado: Clientes por advogado
            
            Use os endpoints POST /cache/* para gerenciar
            """);
    }
}

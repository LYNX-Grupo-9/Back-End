package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.processo.AtualizarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.processo.CriarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.*;
import br.com.exemplo.crudadvogado.core.application.usecase.processo.*;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    @Autowired
    private ObjectMapper objectMapper;

    private final CriarProcessoUseCase criarProcessoUseCase;
    private final ListarProcessosPorAdvogadoUseCase listarProcessosPorAdvogadoUseCase;
    private final ListarProcessosPorClienteUseCase listarProcessosPorClienteUseCase;
    private final ExcluirProcessoPorIdUseCase excluirProcessoPorIdUseCase;
    private final ListarProcessosAtivosPorAdvogadoUseCase listarProcessosAtivosPorAdvogadoUseCase;
    private final ContarProcessosPorStatusPorAdvogadoUseCase contarProcessosPorStatusPorAdvogadoUseCase;
    private final BuscarProcessosPorTextoUseCase buscarProcessosPorTextoUseCase;
    private final ListarProcessosOrdenadosPorStatusUseCase listarProcessosOrdenadosPorStatusUseCase;
    private final CalcularValorMedioProcessosUseCase calcularValorMedioProcessosUseCase;
    private final ListarProcessosOrdenadosPorValorUseCase listarProcessosOrdenadosPorValorUseCase;
    private final ListarProcessosOrdenadosPorNomeClienteUseCase listarProcessosOrdenadosPorNomeClienteUseCase;
    private final ListarProcessosOrdenadosPorNumeroProcessoUseCase listarProcessosOrdenadosPorNumeroProcessoUseCase;
    private final AtualizarProcessoParcialmenteUseCase atualizarProcessoParcialmenteUseCase;
    private final ContarProcessosPorClasseProcessualUseCase contarProcessosPorClasseProcessualPorAdvogadoUseCase;
    private final BuscarProcessoPorIdUseCase buscarProcessoPorIdUseCase;
    private final ListarProcessosPaginadoUseCase listarProcessosPaginadoUseCase;

    public ProcessoController(
            CriarProcessoUseCase criarProcessoUseCase,
            ListarProcessosPorAdvogadoUseCase listarProcessosPorAdvogadoUseCase,
            ListarProcessosPorClienteUseCase listarProcessosPorClienteUseCase,
            ExcluirProcessoPorIdUseCase excluirProcessoPorIdUseCase,
            ListarProcessosAtivosPorAdvogadoUseCase listarProcessosAtivosPorAdvogadoUseCase,
            ContarProcessosPorStatusPorAdvogadoUseCase contarProcessosPorStatusPorAdvogadoUseCase,
            BuscarProcessosPorTextoUseCase buscarProcessosPorTextoUseCase,
            ListarProcessosOrdenadosPorStatusUseCase listarProcessosOrdenadosPorStatusUseCase,
            CalcularValorMedioProcessosUseCase calcularValorMedioProcessosUseCase,
            ListarProcessosOrdenadosPorValorUseCase listarProcessosOrdenadosPorValorUseCase,
            ListarProcessosOrdenadosPorNomeClienteUseCase listarProcessosOrdenadosPorNomeClienteUseCase,
            ListarProcessosOrdenadosPorNumeroProcessoUseCase listarProcessosOrdenadosPorNumeroProcessoUseCase,
            AtualizarProcessoParcialmenteUseCase atualizarProcessoParcialmenteUseCase,
            ContarProcessosPorClasseProcessualUseCase contarProcessosPorClasseProcessualPorAdvogadoUseCase,
            BuscarProcessoPorIdUseCase buscarProcessoPorIdUseCase,
            ListarProcessosPaginadoUseCase listarProcessosPaginadoUseCase
    ) {
        this.criarProcessoUseCase = criarProcessoUseCase;
        this.listarProcessosPorAdvogadoUseCase = listarProcessosPorAdvogadoUseCase;
        this.listarProcessosPorClienteUseCase = listarProcessosPorClienteUseCase;
        this.excluirProcessoPorIdUseCase = excluirProcessoPorIdUseCase;
        this.listarProcessosAtivosPorAdvogadoUseCase = listarProcessosAtivosPorAdvogadoUseCase;
        this.contarProcessosPorStatusPorAdvogadoUseCase = contarProcessosPorStatusPorAdvogadoUseCase;
        this.buscarProcessosPorTextoUseCase = buscarProcessosPorTextoUseCase;
        this.listarProcessosOrdenadosPorStatusUseCase = listarProcessosOrdenadosPorStatusUseCase;
        this.calcularValorMedioProcessosUseCase = calcularValorMedioProcessosUseCase;
        this.listarProcessosOrdenadosPorValorUseCase = listarProcessosOrdenadosPorValorUseCase;
        this.listarProcessosOrdenadosPorNomeClienteUseCase = listarProcessosOrdenadosPorNomeClienteUseCase;
        this.listarProcessosOrdenadosPorNumeroProcessoUseCase = listarProcessosOrdenadosPorNumeroProcessoUseCase;
        this.atualizarProcessoParcialmenteUseCase = atualizarProcessoParcialmenteUseCase;
        this.contarProcessosPorClasseProcessualPorAdvogadoUseCase = contarProcessosPorClasseProcessualPorAdvogadoUseCase;
        this.buscarProcessoPorIdUseCase = buscarProcessoPorIdUseCase;
        this.listarProcessosPaginadoUseCase = listarProcessosPaginadoUseCase;
    }

    @PostMapping("")
    @SecurityRequirement(name = "Bearer")
    public CriarProcessoResponse cadastrar(@RequestBody CriarProcessoCommand command) {
        return criarProcessoUseCase.executar(command);
    }

    @GetMapping("/advogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarPorAdvogado(@PathVariable UUID idAdvogado) {
        List<ProcessoResponse> processos = listarProcessosPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/cliente/{idCliente}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarPorCliente(@PathVariable UUID idCliente) {
        List<ProcessoResponse> processos = listarProcessosPorClienteUseCase.executar(idCliente);
        return ResponseEntity.ok(processos);
    }

    @DeleteMapping("/{idProcesso}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> excluirPorId(@PathVariable UUID idProcesso) {
        excluirProcessoPorIdUseCase.executar(idProcesso);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/processosAtivos/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarProcessosAtivosPorAdvogado(@PathVariable UUID idAdvogado) {
        List<ProcessoResponse> processos = listarProcessosAtivosPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/contagem-por-status/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ContadorProcessosPorStatusResponse> contarProcessosPorStatus(@PathVariable UUID idAdvogado) {
        ContadorProcessosPorStatusResponse contagem = contarProcessosPorStatusPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(contagem);
    }

    @GetMapping("/buscar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> buscarPorTexto(
            @RequestParam String termo,
            @RequestParam UUID idAdvogado) {

        List<ProcessoResponse> processos = buscarProcessosPorTextoUseCase.executar(termo, idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-status")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarOrdenadosPorStatus(@PathVariable UUID idAdvogado) {
        List<ProcessoResponse> processos = listarProcessosOrdenadosPorStatusUseCase.executar(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/media-valor/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ValorMedioProcessosResponse> calcularValorMedio(@PathVariable UUID idAdvogado) {
        ValorMedioProcessosResponse valorMedio = calcularValorMedioProcessosUseCase.executar(idAdvogado);
        return ResponseEntity.ok(valorMedio);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-valor")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ProcessoResponse>> listarOrdenadosPorValor(@PathVariable UUID idAdvogado) {
        List<ProcessoResponse> processos = listarProcessosOrdenadosPorValorUseCase.executar(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/advogados/{idAdvogado}/processos/ordenados-por-cliente")
    @SecurityRequirement(name = "Bearer")
    public List<ProcessoResponse> listarProcessosOrdenadosPorNomeCliente(@PathVariable UUID idAdvogado) {
        return listarProcessosOrdenadosPorNomeClienteUseCase.executar(idAdvogado);
    }

    @GetMapping("/advogados/{idAdvogado}/processos/ordenados-por-numero")
    @SecurityRequirement(name = "Bearer")
    public List<ProcessoResponse> listarProcessosOrdenadosPorNumeroProcesso(@PathVariable UUID idAdvogado) {
        return listarProcessosOrdenadosPorNumeroProcessoUseCase.executar(idAdvogado);
    }

    @PatchMapping("/{idProcesso}")
    @SecurityRequirement(name = "Bearer")
    public ProcessoResponse atualizarProcessoParcialmente(
            @PathVariable UUID idProcesso,
            @RequestBody AtualizarProcessoCommand command) {
        return atualizarProcessoParcialmenteUseCase.executar(idProcesso, command);
    }

    @GetMapping("/quantidade-por-classe/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ContadorProcessosPorClasseProcessualResponse contarProcessosPorClasseProcessual(
            @PathVariable UUID idAdvogado) {
        return contarProcessosPorClasseProcessualPorAdvogadoUseCase.executar(idAdvogado);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ProcessoResponse> buscarPorId(@PathVariable UUID id) {
        ProcessoResponse processo = buscarProcessoPorIdUseCase.executar(id);
        return ResponseEntity.ok(processo);
    }

    @GetMapping("/paginado")
    @SecurityRequirement(name = "Bearer")
    public Page<ProcessoResponse> listarPaginado(@ParameterObject Pageable pageable) throws JsonProcessingException {

        Object raw = listarProcessosPaginadoUseCase.executar(pageable);

        PageCacheDTO<ProcessoResponse> dto;

        if (raw instanceof PageCacheDTO<?> pageDTO) {
            dto = (PageCacheDTO<ProcessoResponse>) pageDTO;
        } else {
            dto = objectMapper.convertValue(raw, new TypeReference<PageCacheDTO<ProcessoResponse>>() {});
        }

        int safeSize = dto.pageSize() < 1 ? 1 : dto.pageSize();
        int safePage = dto.pageNumber() < 0 ? 0 : dto.pageNumber();

        Pageable springPageable = PageRequest.of(safePage, safeSize);

        return new PageImpl<>(
                dto.content(),
                springPageable,
                dto.totalElements()
        );
    }

    // ==========================
    // Gestão de caches
    // ==========================

    @PostMapping("/cache/paginado/limpar")
    @SecurityRequirement(name = "Bearer")
    @CacheEvict(value = "processosPaginados", allEntries = true)
    public ResponseEntity<String> limparCacheProcessosPaginados() {
        return ResponseEntity.ok("Cache de processos paginados limpo com sucesso!");
    }

    @PostMapping("/cache/limpar-tudo")
    @SecurityRequirement(name = "Bearer")
    @CacheEvict(value = {"processosPaginados"}, allEntries = true)
    public ResponseEntity<String> limparTodosCachesProcessos() {
        return ResponseEntity.ok("Todos os caches de processos foram limpos com sucesso!");
    }

    @GetMapping("/cache/status")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> statusCache() {
        return ResponseEntity.ok("""
            Sistema de Cache de Processos Ativo!

            Caches disponíveis:
            - processosPaginados: Consultas paginadas de processos (10min TTL)

            """);
    }

    @PostMapping("/cache/por-advogado/limpar")
    @SecurityRequirement(name = "Bearer")
    @CacheEvict(value = "processosPorAdvogado", allEntries = true)
    public ResponseEntity<String> limparCacheProcessosPorAdvogado() {
        return ResponseEntity.ok("Cache de processos por advogado limpo com sucesso!");
    }

    @PostMapping("/cache/por-cliente/limpar")
    @SecurityRequirement(name = "Bearer")
    @CacheEvict(value = "processosPorCliente", allEntries = true)
    public ResponseEntity<String> limparCacheProcessosPorCliente() {
        return ResponseEntity.ok("Cache de processos por cliente limpo com sucesso!");
    }
}

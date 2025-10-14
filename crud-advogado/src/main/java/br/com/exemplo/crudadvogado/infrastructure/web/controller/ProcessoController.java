package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.processo.AtualizarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.processo.CriarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ContadorProcessosPorStatusResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.CriarProcessoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ValorMedioProcessosResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.processo.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

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

    public ProcessoController(CriarProcessoUseCase criarProcessoUseCase, ListarProcessosPorAdvogadoUseCase listarProcessosPorAdvogadoUseCase, ListarProcessosPorClienteUseCase listarProcessosPorClienteUseCase, ExcluirProcessoPorIdUseCase excluirProcessoPorIdUseCase, ListarProcessosAtivosPorAdvogadoUseCase listarProcessosAtivosPorAdvogadoUseCase, ContarProcessosPorStatusPorAdvogadoUseCase contarProcessosPorStatusPorAdvogadoUseCase, BuscarProcessosPorTextoUseCase buscarProcessosPorTextoUseCase, ListarProcessosOrdenadosPorStatusUseCase listarProcessosOrdenadosPorStatusUseCase, CalcularValorMedioProcessosUseCase calcularValorMedioProcessosUseCase, ListarProcessosOrdenadosPorValorUseCase listarProcessosOrdenadosPorValorUseCase, ListarProcessosOrdenadosPorNomeClienteUseCase listarProcessosOrdenadosPorNomeClienteUseCase, ListarProcessosOrdenadosPorNumeroProcessoUseCase listarProcessosOrdenadosPorNumeroProcessoUseCase, AtualizarProcessoParcialmenteUseCase atualizarProcessoParcialmenteUseCase) {
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
    }

    @PostMapping("/cadastrar")
    @SecurityRequirement(name = "Bearer")
    public CriarProcessoResponse cadastrar(@RequestBody CriarProcessoCommand command) {
        return criarProcessoUseCase.executar(command);
    }

    @GetMapping("/advogado/{idAdvogado}")
    public ResponseEntity<List<ProcessoResponse>> listarPorAdvogado(@PathVariable UUID idAdvogado) {
        List<ProcessoResponse> processos = listarProcessosPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ProcessoResponse>> listarPorCliente(@PathVariable UUID idCliente) {
        List<ProcessoResponse> processos = listarProcessosPorClienteUseCase.executar(idCliente);
        return ResponseEntity.ok(processos);
    }

    @DeleteMapping("/{idProcesso}")
    public ResponseEntity<Void> excluirPorId(@PathVariable UUID idProcesso) {
        excluirProcessoPorIdUseCase.executar(idProcesso);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/advogado/{idAdvogado}/ativos")
    public ResponseEntity<List<ProcessoResponse>> listarProcessosAtivosPorAdvogado(@PathVariable UUID idAdvogado) {
        List<ProcessoResponse> processos = listarProcessosAtivosPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/advogado/{idAdvogado}/contagem-status")
    public ResponseEntity<ContadorProcessosPorStatusResponse> contarProcessosPorStatus(@PathVariable UUID idAdvogado) {
        ContadorProcessosPorStatusResponse contagem = contarProcessosPorStatusPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(contagem);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProcessoResponse>> buscarPorTexto(
            @RequestParam String termo,
            @RequestParam UUID idAdvogado) {

        List<ProcessoResponse> processos = buscarProcessosPorTextoUseCase.executar(termo, idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-status")
    public ResponseEntity<List<ProcessoResponse>> listarOrdenadosPorStatus(@PathVariable UUID idAdvogado) {
        List<ProcessoResponse> processos = listarProcessosOrdenadosPorStatusUseCase.executar(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/advogado/{idAdvogado}/valor-medio")
    public ResponseEntity<ValorMedioProcessosResponse> calcularValorMedio(@PathVariable UUID idAdvogado) {
        ValorMedioProcessosResponse valorMedio = calcularValorMedioProcessosUseCase.executar(idAdvogado);
        return ResponseEntity.ok(valorMedio);
    }

    @GetMapping("/advogado/{idAdvogado}/ordenados-valor")
    public ResponseEntity<List<ProcessoResponse>> listarOrdenadosPorValor(@PathVariable UUID idAdvogado) {
        List<ProcessoResponse> processos = listarProcessosOrdenadosPorValorUseCase.executar(idAdvogado);
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/advogados/{idAdvogado}/processos/ordenados-por-cliente")
    public List<ProcessoResponse> listarProcessosOrdenadosPorNomeCliente(@PathVariable UUID idAdvogado) {
        return listarProcessosOrdenadosPorNomeClienteUseCase.executar(idAdvogado);
    }

    @GetMapping("/advogados/{idAdvogado}/processos/ordenados-por-numero")
    public List<ProcessoResponse> listarProcessosOrdenadosPorNumeroProcesso(@PathVariable UUID idAdvogado) {
        return listarProcessosOrdenadosPorNumeroProcessoUseCase.executar(idAdvogado);
    }

    @PatchMapping("/{idProcesso}")
    public ProcessoResponse atualizarProcessoParcialmente(
            @PathVariable UUID idProcesso,
            @RequestBody AtualizarProcessoCommand command) {
        return atualizarProcessoParcialmenteUseCase.executar(idProcesso, command);
    }
}

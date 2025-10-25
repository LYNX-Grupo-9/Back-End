package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento.CriarSolicitacaoAgendamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento.MarcarComoVisualizadoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.CriarSolicitacaoAgendamentoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.MarcarComoVisualizadoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.BuscarSolicitacaoPorIdUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.BuscarSolicitacoesPorAdvogadoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.CriarSolicitacaoAgendamentoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.MarcarComoVisualizadoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/solicitacao-agendamento")
public class SolicitacaoAgendamentoController {

    private final CriarSolicitacaoAgendamentoUseCase criarSolicitacaoAgendamentoUseCase;
    private final MarcarComoVisualizadoUseCase marcarComoVisualizadoUseCase;
    private final BuscarSolicitacaoPorIdUseCase buscarSolicitacaoPorIdUseCase;
    private final BuscarSolicitacoesPorAdvogadoUseCase buscarSolicitacoesPorAdvogadoUseCase;

    public SolicitacaoAgendamentoController(CriarSolicitacaoAgendamentoUseCase criarSolicitacaoAgendamentoUseCase, MarcarComoVisualizadoUseCase marcarComoVisualizadoUseCase, BuscarSolicitacaoPorIdUseCase buscarSolicitacaoPorIdUseCase, BuscarSolicitacoesPorAdvogadoUseCase buscarSolicitacoesPorAdvogadoUseCase) {
        this.criarSolicitacaoAgendamentoUseCase = criarSolicitacaoAgendamentoUseCase;
        this.marcarComoVisualizadoUseCase = marcarComoVisualizadoUseCase;
        this.buscarSolicitacaoPorIdUseCase = buscarSolicitacaoPorIdUseCase;
        this.buscarSolicitacoesPorAdvogadoUseCase = buscarSolicitacoesPorAdvogadoUseCase;
    }


    @PostMapping("/solicitar")
    public CriarSolicitacaoAgendamentoResponse solicitarAgendamento(@RequestBody CriarSolicitacaoAgendamentoCommand command){
        return criarSolicitacaoAgendamentoUseCase.executar(command);
    }

    @PutMapping("/visualizar/{idSolicitacao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<MarcarComoVisualizadoResponse> marcarVisualizado(@PathVariable Long idSolicitacao) {
        MarcarComoVisualizadoCommand command = new MarcarComoVisualizadoCommand(idSolicitacao);
        MarcarComoVisualizadoResponse response = marcarComoVisualizadoUseCase.executar(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/advogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<MarcarComoVisualizadoResponse>> buscarPorAdvogado(@PathVariable UUID idAdvogado) {
        List<MarcarComoVisualizadoResponse> lista = buscarSolicitacoesPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/solicitacao/{idSolicitacao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<MarcarComoVisualizadoResponse> buscarPorId(@PathVariable Long idSolicitacao) {
        MarcarComoVisualizadoResponse response = buscarSolicitacaoPorIdUseCase.executar(idSolicitacao);
        return ResponseEntity.ok(response);
    }

}

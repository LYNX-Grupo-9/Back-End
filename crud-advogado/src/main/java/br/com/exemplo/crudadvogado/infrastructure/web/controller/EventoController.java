package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.evento.AtualizarEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.evento.CriarEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.CriarEventoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.EventoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.application.usecase.evento.*;
import br.com.exemplo.crudadvogado.core.application.usecase.processo.ContarProcessosPorClasseProcessualUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final CriarEventoUseCase criarEventoUseCase;
    private final BuscarEventoPorIdUseCase buscarEventoPorIdUseCase;
    private final BuscarEventosPorAdvogadoUseCase buscarEventosPorAdvogadoUseCase;
    private final BuscarEventosProximosSeteDiasUseCase buscarEventosProximosSeteDiasUseCase;
    private final DeletarEventoUseCase deletarEventoUseCase;
    private final BuscarProximoEventoUseCase buscarProximoEventoUseCase;
    private final ContarEventosDoDiaUseCase contarEventosDoDiaUseCase;
    private final BuscarEventosDoMesUseCase buscarEventosDoMesUseCase;
    private final AtualizarEventoParcialUseCase atualizarEventoParcialUseCase;
    private final ContarProcessosPorClasseProcessualUseCase contarProcessosPorClasseProcessualUseCase;

    public EventoController(CriarEventoUseCase criarEventoUseCase, BuscarEventoPorIdUseCase buscarEventoPorIdUseCase, BuscarEventosPorAdvogadoUseCase buscarEventosPorAdvogadoUseCase, BuscarEventosProximosSeteDiasUseCase buscarEventosProximosSeteDiasUseCase, DeletarEventoUseCase deletarEventoUseCase, BuscarProximoEventoUseCase buscarProximoEventoUseCase, ContarEventosDoDiaUseCase contarEventosDoDiaUseCase, BuscarEventosDoMesUseCase buscarEventosDoMesUseCase, AtualizarEventoParcialUseCase atualizarEventoParcialUseCase, ContarProcessosPorClasseProcessualUseCase contarProcessosPorClasseProcessualUseCase) {
        this.criarEventoUseCase = criarEventoUseCase;
        this.buscarEventoPorIdUseCase = buscarEventoPorIdUseCase;
        this.buscarEventosPorAdvogadoUseCase = buscarEventosPorAdvogadoUseCase;
        this.buscarEventosProximosSeteDiasUseCase = buscarEventosProximosSeteDiasUseCase;
        this.deletarEventoUseCase = deletarEventoUseCase;
        this.buscarProximoEventoUseCase = buscarProximoEventoUseCase;
        this.contarEventosDoDiaUseCase = contarEventosDoDiaUseCase;
        this.buscarEventosDoMesUseCase = buscarEventosDoMesUseCase;
        this.atualizarEventoParcialUseCase = atualizarEventoParcialUseCase;
        this.contarProcessosPorClasseProcessualUseCase = contarProcessosPorClasseProcessualUseCase;
    }


    @PostMapping("/criar")
    @SecurityRequirement(name = "Bearer")
    public CriarEventoResponse criar(@RequestBody CriarEventoCommand command) {
        return criarEventoUseCase.executar(command);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarPorId(@PathVariable Long id) {
        try {
            EventoResponse response = buscarEventoPorIdUseCase.executar(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/advogado/{idAdvogado}")
    public ResponseEntity<List<EventoResponse>> buscarPorAdvogado(@PathVariable UUID idAdvogado) {
        try {
            List<EventoResponse> response = buscarEventosPorAdvogadoUseCase.executar(idAdvogado);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/advogado/{idAdvogado}/proximos-7-dias")
    public ResponseEntity<List<EventoResponse>> buscarProximosSeteDias(@PathVariable UUID idAdvogado) {
        try {
            List<EventoResponse> response = buscarEventosProximosSeteDiasUseCase.executar(idAdvogado);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            deletarEventoUseCase.executar(id);
            return ResponseEntity.noContent().build();
        } catch (EventoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/advogado/{idAdvogado}/proximo")
    public ResponseEntity<EventoResponse> buscarProximoEvento(@PathVariable UUID idAdvogado) {
        try {
            Optional<EventoResponse> response = buscarProximoEventoUseCase.executar(idAdvogado);
            return response.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.noContent().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/advogado/{idAdvogado}/contagem-dia")
    public ResponseEntity<Map<String, Long>> contarEventosDoDia(@PathVariable UUID idAdvogado) {
        Map<String, Long> resultado = contarEventosDoDiaUseCase.executar(idAdvogado);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/advogado/{idAdvogado}/mes-atual")
    public ResponseEntity<List<EventoResponse>> buscarEventosDoMes(@PathVariable UUID idAdvogado) {
        List<EventoResponse> eventos = buscarEventosDoMesUseCase.executar(idAdvogado);
        return ResponseEntity.ok(eventos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventoResponse> atualizarParcialmente(
            @PathVariable Long id,
            @RequestBody AtualizarEventoCommand command) {
        EventoResponse response = atualizarEventoParcialUseCase.executar(id, command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/advogados/{idAdvogado}/processos/contagem-por-classe")
    public Map<String, Long> contarProcessosPorClasseProcessual(@PathVariable UUID idAdvogado) {
        return contarProcessosPorClasseProcessualUseCase.executar(idAdvogado);
    }
}

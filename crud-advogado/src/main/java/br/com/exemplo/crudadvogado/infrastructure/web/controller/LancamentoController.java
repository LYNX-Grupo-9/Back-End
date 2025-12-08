package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.lancamento.CriarLancamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.lancamento.CriarLancamentoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.lancamento.LancamentoComParcelasResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.lancamento.BuscarLancamentosPorAdvogadoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.lancamento.CriarLancamentoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private final CriarLancamentoUseCase criarLancamentoUseCase;
    private final BuscarLancamentosPorAdvogadoUseCase buscarLancamentosPorAdvogadoUseCase;

    public LancamentoController(CriarLancamentoUseCase criarLancamentoUseCase,
                                BuscarLancamentosPorAdvogadoUseCase buscarLancamentosPorAdvogadoUseCase) {
        this.criarLancamentoUseCase = criarLancamentoUseCase;
        this.buscarLancamentosPorAdvogadoUseCase = buscarLancamentosPorAdvogadoUseCase;
    }

    @PostMapping("")
    @SecurityRequirement(name = "Bearer")
    public CriarLancamentoResponse criarLancamento(@RequestBody CriarLancamentoCommand command){
        return criarLancamentoUseCase.executar(command);
    }

    @GetMapping("/advogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<LancamentoComParcelasResponse>> buscarPorAdvogado(
            @PathVariable UUID idAdvogado) {

        var lancamentos = buscarLancamentosPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(lancamentos);
    }
}

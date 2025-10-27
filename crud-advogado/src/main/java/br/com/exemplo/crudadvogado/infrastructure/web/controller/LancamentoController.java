package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.lancamento.CriarLancamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.lancamento.CriarLancamentoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.lancamento.CriarLancamentoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private final CriarLancamentoUseCase criarLancamentoUseCase;

    public LancamentoController(CriarLancamentoUseCase criarLancamentoUseCase) {
        this.criarLancamentoUseCase = criarLancamentoUseCase;
    }

    @PostMapping("")
    @SecurityRequirement(name = "Bearer")
    public CriarLancamentoResponse criarLancamento(@RequestBody CriarLancamentoCommand command){
        return criarLancamentoUseCase.executar(command);
    }

}

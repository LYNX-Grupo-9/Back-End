package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.parcela.AtualizarParcelaCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.parcela.CriarParcelaCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.parcela.CriarParcelaResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.parcela.ParcelaResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.parcela.AtualizarParcelaParcialmenteUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.parcela.CriarParcelaUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/parcelas")
public class ParcelaController {

    private final CriarParcelaUseCase criarParcelaUseCase;
    private final AtualizarParcelaParcialmenteUseCase atualizarParcelaParcialmenteUseCase;

    public ParcelaController(CriarParcelaUseCase criarParcelaUseCase, AtualizarParcelaParcialmenteUseCase atualizarParcelaParcialmenteUseCase) {
        this.criarParcelaUseCase = criarParcelaUseCase;
        this.atualizarParcelaParcialmenteUseCase = atualizarParcelaParcialmenteUseCase;
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public CriarParcelaResponse criarParcela(@RequestBody CriarParcelaCommand command){
        return criarParcelaUseCase.executar(command);
    }

    @PatchMapping("/{idParcela}")
    @SecurityRequirement(name = "Bearer")
    public ParcelaResponse atualizarParcelaParcialmente(
            @PathVariable Long idParcela,
            @RequestBody AtualizarParcelaCommand command) {
        return atualizarParcelaParcialmenteUseCase.executar(idParcela, command);
    }
}

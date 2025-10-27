package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.parcela.CriarParcelaCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.parcela.CriarParcelaResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.parcela.CriarParcelaUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parcelas")
public class ParcelaController {

    private final CriarParcelaUseCase criarParcelaUseCase;

    public ParcelaController(CriarParcelaUseCase criarParcelaUseCase) {
        this.criarParcelaUseCase = criarParcelaUseCase;
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public CriarParcelaResponse criarParcela(@RequestBody CriarParcelaCommand command){
        return criarParcelaUseCase.executar(command);
    }

}

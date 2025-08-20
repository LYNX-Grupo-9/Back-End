package br.com.exemplo.crudadvogado.core.controller;

import br.com.exemplo.crudadvogado.core.domain.Advogado;
import br.com.exemplo.crudadvogado.core.dto.advogado.AdvogadoDTO;
import br.com.exemplo.crudadvogado.core.dto.advogado.NovoAdvogadoDTO;
import br.com.exemplo.crudadvogado.core.usecase.advogado.CadastrarAdvogadoUseCase;
import org.springframework.web.bind.annotation.PostMapping;

public class AdvogadoController {
    private final CadastrarAdvogadoUseCase cadastrarAdvogadoUseCase;

    public AdvogadoController(CadastrarAdvogadoUseCase cadastrarAdvogadoUseCase) {
        this.cadastrarAdvogadoUseCase = cadastrarAdvogadoUseCase;
    }

    @PostMapping
    public AdvogadoDTO cadastrarAdvogado(NovoAdvogadoDTO dto) {
        Advogado novo = new Advogado(null, dto.getNome(), dto.getRegistroOab(), dto.getCpf(), dto.getEmail(), dto.getSenha());
        Advogado salvo = cadastrarAdvogadoUseCase.executar(novo);
        return new AdvogadoDTO(salvo.getIdAdvogado(), salvo.getNome(), salvo.getRegistroOab(), salvo.getEmail());
    }
}

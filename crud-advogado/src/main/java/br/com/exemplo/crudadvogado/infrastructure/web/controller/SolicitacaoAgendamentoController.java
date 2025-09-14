package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento.CriarSolicitacaoAgendamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.CriarSolicitacaoAgendamentoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.CriarSolicitacaoAgendamentoUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solicitacoes-agendamento")
public class SolicitacaoAgendamentoController {

    private final CriarSolicitacaoAgendamentoUseCase criarSolicitacaoAgendamentoUseCase;

    public SolicitacaoAgendamentoController(CriarSolicitacaoAgendamentoUseCase criarSolicitacaoAgendamentoUseCase) {
        this.criarSolicitacaoAgendamentoUseCase = criarSolicitacaoAgendamentoUseCase;
    }

    @PostMapping
    public CriarSolicitacaoAgendamentoResponse solicitarAgendamento(@RequestBody CriarSolicitacaoAgendamentoCommand command){
        return criarSolicitacaoAgendamentoUseCase.executar(command);
    }

}

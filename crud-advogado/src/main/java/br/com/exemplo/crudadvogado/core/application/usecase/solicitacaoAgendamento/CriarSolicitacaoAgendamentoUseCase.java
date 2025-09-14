package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento.CriarSolicitacaoAgendamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.CriarSolicitacaoAgendamentoResponse;
import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;

public class CriarSolicitacaoAgendamentoUseCase {

    private final SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway;

    public CriarSolicitacaoAgendamentoUseCase(SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway) {
        this.solicitacaoAgendamentoGateway = solicitacaoAgendamentoGateway;
    }

    public CriarSolicitacaoAgendamentoResponse executar(CriarSolicitacaoAgendamentoCommand command){
        SolicitacaoAgendamento solicitacaoParaRegistrar = SolicitacaoAgendamento.criarNovo(
                command.nome(),
                command.telefone(),
                command.email(),
                command.assunto(),
                command.dataSolicitacao(),
                command.horaSolicitacao(),
                command.idAdvogado()
        );
        SolicitacaoAgendamento solicitacaoCriada = solicitacaoAgendamentoGateway.criar(solicitacaoParaRegistrar);

        return new CriarSolicitacaoAgendamentoResponse(
                solicitacaoCriada.getIdSolicitacaoAgendamento(),
                solicitacaoCriada.getNome(),
                solicitacaoCriada.getTelefone(),
                solicitacaoCriada.getEmail(),
                solicitacaoCriada.getAssunto(),
                solicitacaoCriada.getDataSolicitacao(),
                solicitacaoCriada.getHoraSolicitacao(),
                solicitacaoCriada.getIdAdvogado()
        );
    }
}

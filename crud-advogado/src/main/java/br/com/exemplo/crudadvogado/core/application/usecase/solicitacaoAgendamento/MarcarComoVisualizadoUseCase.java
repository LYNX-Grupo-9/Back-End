package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento.MarcarComoVisualizadoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.MarcarComoVisualizadoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.SolicitacaoAgendamentoNaoEncontradaException;
import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;

public class MarcarComoVisualizadoUseCase {

    private final SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway;

    public MarcarComoVisualizadoUseCase(SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway) {
        this.solicitacaoAgendamentoGateway = solicitacaoAgendamentoGateway;
    }

    public MarcarComoVisualizadoResponse executar(MarcarComoVisualizadoCommand command) {
        SolicitacaoAgendamento solicitacao = solicitacaoAgendamentoGateway
                .buscarPorId(command.idSolicitacao())
                .orElseThrow(() -> new SolicitacaoAgendamentoNaoEncontradaException(
                        "Solicitação de agendamento não encontrada com ID: " + command.idSolicitacao()));

        solicitacao.marcarComoVisualizado();

        SolicitacaoAgendamento solicitacaoAtualizada = solicitacaoAgendamentoGateway.atualizar(solicitacao);

        return new MarcarComoVisualizadoResponse(solicitacaoAtualizada);
    }
}

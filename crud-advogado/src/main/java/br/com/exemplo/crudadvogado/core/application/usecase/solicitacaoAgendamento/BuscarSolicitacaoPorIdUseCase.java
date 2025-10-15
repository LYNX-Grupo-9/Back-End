package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.MarcarComoVisualizadoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.SolicitacaoAgendamentoNaoEncontradaException;

import java.util.UUID;

public class BuscarSolicitacaoPorIdUseCase {

    private final SolicitacaoAgendamentoGateway gateway;

    public BuscarSolicitacaoPorIdUseCase(SolicitacaoAgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    public MarcarComoVisualizadoResponse executar(UUID idSolicitacao) {
        var solicitacao = gateway.buscarPorId(idSolicitacao)
                .orElseThrow(() -> new SolicitacaoAgendamentoNaoEncontradaException(
                        "Solicitação não encontrada com ID: " + idSolicitacao));
        return new MarcarComoVisualizadoResponse(solicitacao);
    }
}

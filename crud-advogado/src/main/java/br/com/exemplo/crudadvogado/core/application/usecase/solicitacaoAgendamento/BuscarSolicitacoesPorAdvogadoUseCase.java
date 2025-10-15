package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.MarcarComoVisualizadoResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuscarSolicitacoesPorAdvogadoUseCase {
    private final SolicitacaoAgendamentoGateway gateway;

    public BuscarSolicitacoesPorAdvogadoUseCase(SolicitacaoAgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    public List<MarcarComoVisualizadoResponse> executar(UUID idAdvogado) {
        var solicitacoes = gateway.buscarPorAdvogado(idAdvogado);
        return solicitacoes.stream()
                .map(MarcarComoVisualizadoResponse::new)
                .collect(Collectors.toList());
    }
}

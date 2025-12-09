package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SolicitacaoAgendamentoGateway {
    SolicitacaoAgendamento criar(SolicitacaoAgendamento domain);
    Optional<SolicitacaoAgendamento> buscarPorId(Long id);
    SolicitacaoAgendamento atualizar(SolicitacaoAgendamento solicitacao);
    List<SolicitacaoAgendamento> buscarPorAdvogado(UUID idAdvogado);
}

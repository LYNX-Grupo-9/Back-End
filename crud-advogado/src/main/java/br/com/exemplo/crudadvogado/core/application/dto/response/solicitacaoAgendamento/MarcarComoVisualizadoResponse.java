package br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public record MarcarComoVisualizadoResponse(
        Long idSolicitacao,
        String nome,
        String telefone,
        String email,
        String assunto,
        Date dataSolicitacao,
        LocalTime horaSolicitacao,
        UUID idAdvogado,
        Boolean visualizado
) {
    public MarcarComoVisualizadoResponse(SolicitacaoAgendamento solicitacao) {
        this(
                solicitacao.getIdSolicitacaoAgendamento(),
                solicitacao.getNome(),
                solicitacao.getTelefone(),
                solicitacao.getEmail(),
                solicitacao.getAssunto(),
                solicitacao.getDataSolicitacao(),
                solicitacao.getHoraSolicitacao(),
                solicitacao.getIdAdvogado(),
                solicitacao.getVisualizado()
        );
    }
}

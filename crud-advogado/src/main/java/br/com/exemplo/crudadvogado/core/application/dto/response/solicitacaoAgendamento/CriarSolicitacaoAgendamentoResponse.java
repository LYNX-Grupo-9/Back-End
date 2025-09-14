package br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public record CriarSolicitacaoAgendamentoResponse(
        Long idSolicitacaoAgendamento,
        String nome,
        String telefone,
        String email,
        String assunto,
        Date dataSolicitacao,
        LocalTime horaSolicitacao,
        UUID idAdvogado
) {
}

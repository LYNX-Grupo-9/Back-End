package br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public record CriarSolicitacaoAgendamentoCommand(
        String nome,
        String telefone,
        String email,
        String assunto,
        Date dataSolicitacao,
        LocalTime horaSolicitacao,
        UUID idAdvogado
) {
}

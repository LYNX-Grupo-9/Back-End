package br.com.exemplo.crudadvogado.infrastructure.consumer.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record SolicitacaoAgendamentoMessage(
        Long idSolicitacaoAgendamento,
        String nome,
        String telefone,
        String email,
        String assunto,
        Date dataSolicitacao,
        LocalTime horaSolicitacao,
        Long idAdvogado,
        String status,
        LocalDate dataCriacao
) {
}

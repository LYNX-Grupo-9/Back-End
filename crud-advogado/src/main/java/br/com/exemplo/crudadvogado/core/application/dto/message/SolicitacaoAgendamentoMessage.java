package br.com.exemplo.crudadvogado.core.application.dto.message;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public record SolicitacaoAgendamentoMessage(
        Long idSolicitacaoAgendamento,
        String nome,
        String telefone,
        String email,
        String assunto,
        Date dataSolicitacao,
        LocalTime horaSolicitacao,
        UUID idAdvogado,
        String status,
        LocalDate dataCriacao
) {
    public Double getIdSolicitacao() {
        return 0.0;
    }

    public String getNome() {
        return "";
    }

    public String getAssunto() {
        return "";
    }

    public String getStatus() {
        return "";
    }
}

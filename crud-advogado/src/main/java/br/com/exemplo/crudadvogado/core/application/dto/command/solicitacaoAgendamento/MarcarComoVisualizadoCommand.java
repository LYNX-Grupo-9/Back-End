package br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento;

import java.util.UUID;

public record MarcarComoVisualizadoCommand(
        UUID idSolicitacao
) {
}

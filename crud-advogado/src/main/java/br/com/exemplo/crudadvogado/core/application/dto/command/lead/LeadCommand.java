package br.com.exemplo.crudadvogado.core.application.dto.command.lead;

import java.util.UUID;

public record LeadCommand(
        String nome,
        String telefone,
        String email,
        String assunto,
        String mensagem,
        UUID advogado
) {
}

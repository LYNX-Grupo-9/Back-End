package br.com.exemplo.crudadvogado.core.application.dto.command.lead;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;

import java.util.UUID;

public record LeadCommand(
        String nome,
        String telefone,
        Email email,
        String assunto,
        String mensagem,
        UUID advogado
) {
}

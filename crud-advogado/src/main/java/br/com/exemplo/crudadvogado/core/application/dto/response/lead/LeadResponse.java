package br.com.exemplo.crudadvogado.core.application.dto.response.lead;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;

import java.util.UUID;

public record LeadResponse(
        Long idLead,
        String nome,
        String telefone,
        String email,
        String assunto,
        String mensagem,
        UUID advogado
) {
}

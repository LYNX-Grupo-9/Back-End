package br.com.exemplo.crudadvogado.core.application.dto.response.advogado;

import java.util.UUID;

public record CriarAdvogadoResponse(
        UUID id,
        String nome,
        String email,
        String cpf,
        String oab
) {
}

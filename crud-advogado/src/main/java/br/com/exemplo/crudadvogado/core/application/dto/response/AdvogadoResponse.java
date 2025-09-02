package br.com.exemplo.crudadvogado.core.application.dto.response;

import java.util.UUID;

public record AdvogadoResponse(
        UUID idAdvogado,
        String nome,
        String email,
        String oab
) {
}

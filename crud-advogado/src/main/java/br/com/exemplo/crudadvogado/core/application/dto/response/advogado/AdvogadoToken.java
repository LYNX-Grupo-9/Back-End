package br.com.exemplo.crudadvogado.core.application.dto.response.advogado;

import java.util.UUID;

public record AdvogadoToken(
        UUID idAdvogado,
        String nome,
        String email,
        String token

) {
}

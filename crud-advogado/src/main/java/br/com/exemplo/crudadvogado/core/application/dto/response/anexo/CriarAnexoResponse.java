package br.com.exemplo.crudadvogado.core.application.dto.response.anexo;

import java.util.UUID;

public record CriarAnexoResponse(
        Long idAnexo,
        String nomeAnexo,
        String idItem,
        UUID idCliente,
        UUID idProcesso
) {
}

package br.com.exemplo.crudadvogado.core.application.dto.response.anexo;

import java.util.UUID;

public record AnexoResponse(
        Long idAnexo,
        String nomeAnexo,
        String idItem,
        UUID idCliente,
        UUID idProcesso
) {
}

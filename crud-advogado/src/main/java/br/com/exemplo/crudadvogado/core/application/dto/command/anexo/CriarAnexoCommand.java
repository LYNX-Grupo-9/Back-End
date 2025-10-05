package br.com.exemplo.crudadvogado.core.application.dto.command.anexo;

import java.util.UUID;

public record CriarAnexoCommand(
        String nomeAnexo,
        String idItem, //id do item do bucket
        UUID idCliente,
        UUID idProcesso
) {
}

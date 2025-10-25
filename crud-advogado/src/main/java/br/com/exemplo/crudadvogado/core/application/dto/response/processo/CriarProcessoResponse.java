package br.com.exemplo.crudadvogado.core.application.dto.response.processo;

import java.util.UUID;

public record CriarProcessoResponse(
        UUID idProcesso,
        String titulo,
        String numeroProcesso,
        String descricao,
        String status,
        String classeProcessual,
        String assunto,
        String tribunal,
        String valor,
        String autor,
        String advRequerente,
        String reu,
        String advReu,
        UUID idAdvogado,
        UUID idCliente
) {
}

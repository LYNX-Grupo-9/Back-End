package br.com.exemplo.crudadvogado.core.application.dto.command.processo;

import java.math.BigDecimal;
import java.util.UUID;

public record AtualizarProcessoCommand(
        UUID idProcesso,
        String titulo,
        String numeroProcesso,
        String descricao,
        String status,
        String classeProcessual,
        String assunto,
        String tribunal,
        BigDecimal valor,
        String autor,
        String advRequerente,
        String reu,
        String advReu,
        UUID idAdvogado,
        UUID idCliente
) {
}

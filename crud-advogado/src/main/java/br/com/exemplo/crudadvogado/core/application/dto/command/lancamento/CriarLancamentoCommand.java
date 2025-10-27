package br.com.exemplo.crudadvogado.core.application.dto.command.lancamento;

import java.util.UUID;

public record CriarLancamentoCommand(
        String titulo,
        UUID idProcesso,
        UUID idCliente
) {
}

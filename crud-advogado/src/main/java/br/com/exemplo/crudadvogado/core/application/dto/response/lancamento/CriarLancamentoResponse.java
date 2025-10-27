package br.com.exemplo.crudadvogado.core.application.dto.response.lancamento;

import java.util.UUID;

public record CriarLancamentoResponse(
        Long idLancamento,
        String titulo,
        UUID idProcesso,
        UUID idCliente
) {
}

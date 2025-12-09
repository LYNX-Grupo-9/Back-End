package br.com.exemplo.crudadvogado.core.application.dto.response.lancamento;

import java.util.List;
import java.util.UUID;

public record LancamentoComParcelasResponse(
        Long idLancamentos,
        UUID idCliente,
        UUID idProcesso,
        String titulo,
        List<ParcelaResponse> parcelas
) {}
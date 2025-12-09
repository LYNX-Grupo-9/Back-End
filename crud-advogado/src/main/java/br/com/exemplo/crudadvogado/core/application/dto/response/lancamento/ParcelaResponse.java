package br.com.exemplo.crudadvogado.core.application.dto.response.lancamento;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.parcela.Status;

import java.math.BigDecimal;
import java.util.Date;

public record ParcelaResponse(
        Long idParcelas,
        Long idLancamento,
        BigDecimal valor,
        Date vencimento,
        Status status
) {}

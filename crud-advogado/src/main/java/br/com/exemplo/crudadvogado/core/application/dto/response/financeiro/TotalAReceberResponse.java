package br.com.exemplo.crudadvogado.core.application.dto.response.financeiro;

import java.math.BigDecimal;

public record TotalAReceberResponse(
        BigDecimal totalAReceber,
        BigDecimal percentual
) {}

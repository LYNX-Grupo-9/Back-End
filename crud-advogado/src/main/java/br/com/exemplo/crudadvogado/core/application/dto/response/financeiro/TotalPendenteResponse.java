package br.com.exemplo.crudadvogado.core.application.dto.response.financeiro;

import java.math.BigDecimal;

public record TotalPendenteResponse(
        BigDecimal totalPendente,
        BigDecimal percentual
) {}

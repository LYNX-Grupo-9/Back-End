package br.com.exemplo.crudadvogado.core.application.dto.response.financeiro;

import java.math.BigDecimal;

public record FaturadoUltimos6MesesResponse(
        BigDecimal totalFaturado30,
        BigDecimal totalFaturado60,
        BigDecimal totalFaturado90,
        BigDecimal totalFaturado120,
        BigDecimal totalFaturado150,
        BigDecimal totalFaturado180
) {
}

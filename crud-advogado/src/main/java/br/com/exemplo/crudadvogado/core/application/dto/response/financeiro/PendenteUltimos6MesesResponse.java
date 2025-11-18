package br.com.exemplo.crudadvogado.core.application.dto.response.financeiro;

import java.math.BigDecimal;

public record PendenteUltimos6MesesResponse(
        BigDecimal totalPendente30,
        BigDecimal totalPendente60,
        BigDecimal totalPendente90,
        BigDecimal totalPendente120,
        BigDecimal totalPendente150,
        BigDecimal totalPendente180
) {
}

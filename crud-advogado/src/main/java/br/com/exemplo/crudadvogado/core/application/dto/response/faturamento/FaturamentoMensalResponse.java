package br.com.exemplo.crudadvogado.core.application.dto.response.faturamento;

import java.math.BigDecimal;

public record FaturamentoMensalResponse(
        String mes,
        BigDecimal valor
){
}

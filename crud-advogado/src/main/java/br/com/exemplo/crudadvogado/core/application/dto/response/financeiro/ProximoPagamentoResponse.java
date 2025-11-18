package br.com.exemplo.crudadvogado.core.application.dto.response.financeiro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProximoPagamentoResponse (
        Long idCliente,
        String nomeCliente,
        String status,
        LocalDate vencimento,
        Integer numParcelas,
        Integer parcelaAtual,
        BigDecimal valorTotal
) {
}

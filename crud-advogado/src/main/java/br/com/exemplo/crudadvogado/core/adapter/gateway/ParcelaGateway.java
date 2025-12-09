package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.FaturadoUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.PendenteUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.ProximoPagamentoResponse;
import br.com.exemplo.crudadvogado.core.domain.Parcela;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

public interface ParcelaGateway {
    Parcela criar(Parcela parcela);

    BigDecimal calcularTotalPendenteMesAtual();
    BigDecimal calcularTotalPendenteMesAnterior();

    Long contarClientesComPendenciasAtrasadasMesAtual();
    Long contarClientesComPendenciasAtrasadasMesAnterior();

    Long contarProcessosComPendenciasAtrasadasMesAtual();
    Long contarProcessosComPendenciasAtrasadasMesAnterior();

    BigDecimal calcularTotalAReceberProximos30Dias();
    BigDecimal calcularTotalAReceberUltimos30Dias();

    BigDecimal calcularTotalFaturadoMesAtual();
    BigDecimal calcularTotalFaturadoMesAnterior();

    Optional<ProximoPagamentoResponse> obterProximoPagamento();

    FaturadoUltimos6MesesResponse calcularFaturadoUltimos6Meses();
    PendenteUltimos6MesesResponse calcularPendenteUltimos6Meses();

    Optional<Parcela> buscarPorId(Long id);
    Parcela atualizar(Parcela parcela);
}

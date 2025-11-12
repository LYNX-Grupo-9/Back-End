package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Parcela;

import java.math.BigDecimal;
import java.time.YearMonth;

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
}

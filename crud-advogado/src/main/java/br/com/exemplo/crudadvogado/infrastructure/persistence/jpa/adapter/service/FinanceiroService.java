package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class FinanceiroService {

    private final ParcelaGateway parcelaGateway;

    public FinanceiroService(ParcelaGateway parcelaGateway) {
        this.parcelaGateway = parcelaGateway;
    }

    public TotalPendenteResponse calcularTotalPendenteMes() {
        BigDecimal totalAtual = parcelaGateway.calcularTotalPendenteMesAtual();
        BigDecimal totalAnterior = parcelaGateway.calcularTotalPendenteMesAnterior();

        BigDecimal percentual = calcularVariacaoPercentual(totalAnterior, totalAtual);

        return new TotalPendenteResponse(totalAtual, percentual);
    }

    public ClientesComPendenciasResponse calcularClientesComPendenciasAtrasadas() {
        Long totalAtual = parcelaGateway.contarClientesComPendenciasAtrasadasMesAtual();
        Long totalAnterior = parcelaGateway.contarClientesComPendenciasAtrasadasMesAnterior();

        double percentual = calcularVariacaoPercentualLong(totalAnterior, totalAtual);
        return new ClientesComPendenciasResponse(totalAtual, percentual);
    }

    public ProcessosComPendenciasResponse calcularProcessosComPendenciasAtrasadas() {
        Long totalAtual = parcelaGateway.contarProcessosComPendenciasAtrasadasMesAtual();
        Long totalAnterior = parcelaGateway.contarProcessosComPendenciasAtrasadasMesAnterior();

        double percentual = calcularVariacaoPercentualLong(totalAnterior, totalAtual);
        return new ProcessosComPendenciasResponse(totalAtual, percentual);
    }

    public TotalAReceberResponse calcularTotalAReceberProximos30Dias() {
        BigDecimal totalProximos30 = parcelaGateway.calcularTotalAReceberProximos30Dias();
        BigDecimal totalUltimos30 = parcelaGateway.calcularTotalAReceberUltimos30Dias();

        BigDecimal percentual = calcularVariacaoPercentual(totalUltimos30, totalProximos30);

        return new TotalAReceberResponse(totalProximos30, percentual);
    }

    public TotalFaturadoResponse calcularTotalFaturadoMes() {
        BigDecimal totalAtual = parcelaGateway.calcularTotalFaturadoMesAtual();
        BigDecimal totalAnterior = parcelaGateway.calcularTotalFaturadoMesAnterior();

        BigDecimal percentual = calcularVariacaoPercentual(totalAnterior, totalAtual);

        return new TotalFaturadoResponse(totalAtual, percentual);
    }

    private BigDecimal calcularVariacaoPercentual(BigDecimal anterior, BigDecimal atual) {
        if (anterior == null || anterior.compareTo(BigDecimal.ZERO) == 0) {
            return (atual != null && atual.compareTo(BigDecimal.ZERO) > 0)
                    ? BigDecimal.valueOf(100)
                    : BigDecimal.ZERO;
        }

        return atual.subtract(anterior)
                .divide(anterior, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(1, RoundingMode.HALF_UP);
    }

    private double calcularVariacaoPercentualLong(Long anterior, Long atual) {
        if (anterior == null || anterior == 0) {
            return (atual != null && atual > 0) ? 100.0 : 0.0;
        }

        double variacao = ((double) (atual - anterior) / anterior) * 100;
        return Math.round(variacao * 10.0) / 10.0;
    }

    public Optional<ProximoPagamentoResponse> obterProximoPagamento() {
        return parcelaGateway.obterProximoPagamento();
    }
}
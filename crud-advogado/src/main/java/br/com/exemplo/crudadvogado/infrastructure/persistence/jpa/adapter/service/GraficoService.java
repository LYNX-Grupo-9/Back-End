package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.FaturadoUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.PendenteUltimos6MesesResponse;
import org.springframework.stereotype.Service;

@Service
public class GraficoService {

    private final ParcelaGateway parcelaGateway;

    public GraficoService(ParcelaGateway parcelaGateway) {
        this.parcelaGateway = parcelaGateway;
    }

    public FaturadoUltimos6MesesResponse obterFaturadoUltimos6Meses() {
        return parcelaGateway.calcularFaturadoUltimos6Meses();
    }

    public PendenteUltimos6MesesResponse obterPendenteUltimos6Meses() {
        return parcelaGateway.calcularPendenteUltimos6Meses();
    }
}

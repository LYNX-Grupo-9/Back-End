package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service;

import br.com.exemplo.crudadvogado.core.adapter.gateway.FaturamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.faturamento.FaturamentoMensalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaturamentoService {

    private final FaturamentoGateway faturamentoGateway;

    public FaturamentoService(FaturamentoGateway faturamentoGateway) {
        this.faturamentoGateway = faturamentoGateway;
    }

    public List<FaturamentoMensalResponse> obterFaturamentoUltimos6Meses() {
        return faturamentoGateway.obterFaturamentoUltimos6Meses();
    }
}

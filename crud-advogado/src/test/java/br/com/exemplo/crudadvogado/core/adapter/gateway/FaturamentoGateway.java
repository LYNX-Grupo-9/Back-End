package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.application.dto.response.faturamento.FaturamentoMensalResponse;

import java.util.List;

public interface FaturamentoGateway {
    List<FaturamentoMensalResponse> obterFaturamentoUltimos6Meses();
}

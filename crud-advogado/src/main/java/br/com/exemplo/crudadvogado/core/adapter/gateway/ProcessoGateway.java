package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Processo;

import java.util.UUID;

public interface ProcessoGateway {
    Processo criar(Processo domain);
    Long contarProcessosPorCliente(UUID idCliente);
}

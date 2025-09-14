package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Processo;

public interface ProcessoGateway {
    Processo criar(Processo domain);
}

package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Evento;

public interface EventoGateway {
    Evento criar(Evento domain);
}

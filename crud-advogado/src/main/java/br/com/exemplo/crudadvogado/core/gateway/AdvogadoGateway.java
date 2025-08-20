package br.com.exemplo.crudadvogado.core.gateway;

import br.com.exemplo.crudadvogado.core.domain.Advogado;

import java.util.UUID;

public interface AdvogadoGateway {
    Advogado salvar(Advogado advogado);
    Advogado buscarPorId(UUID id);
}

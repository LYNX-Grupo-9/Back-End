package br.com.exemplo.crudadvogado.core.gateway;

import br.com.exemplo.crudadvogado.core.domain.Cliente;

public interface ClienteGateway {
    Cliente salvar(Cliente cliente);
    Cliente bucarPorId(Long id);
}

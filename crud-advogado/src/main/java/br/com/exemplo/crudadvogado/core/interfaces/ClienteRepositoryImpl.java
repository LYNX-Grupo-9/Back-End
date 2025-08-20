package br.com.exemplo.crudadvogado.core.interfaces;

import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.entities.ClienteEntity;
import br.com.exemplo.crudadvogado.core.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.repository.JpaClienteRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepositoryImpl implements ClienteGateway {

    private final JpaClienteRepository repository;

    public ClienteRepositoryImpl(JpaClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente bucarPorId(Long id) {
        return null;
    }
}

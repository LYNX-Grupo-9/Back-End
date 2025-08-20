package br.com.exemplo.crudadvogado.core.interfaces;

import br.com.exemplo.crudadvogado.core.domain.Advogado;
import br.com.exemplo.crudadvogado.core.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.repository.JpaAdvogadoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AdvogadoRepositoryImpl implements AdvogadoGateway {

    private final JpaAdvogadoRepository repository;

    public AdvogadoRepositoryImpl(JpaAdvogadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Advogado salvar(Advogado advogado) {
        return repository.save(advogado);
    }

    @Override
    public Advogado buscarPorId(UUID id) {
        return repository.findById(id).orElse(null);
    }
}

package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;


import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.domain.Advogado;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.AdvogadoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.AdvogadoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AdvogadoJpaAdapter implements AdvogadoGateway {

    private final AdvogadoJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public AdvogadoJpaAdapter(AdvogadoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Advogado criar(Advogado domain) {
        AdvogadoEntity entityParaRegistro = AdvogadoMapper.toEntity(domain);
        entityManager.persist(entityParaRegistro);
        entityManager.flush();
        return AdvogadoMapper.toDomain(entityParaRegistro);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public boolean existePorOab(String oab) {
        return repository.existsByRegistroOab(oab);
    }

    @Override
    public boolean existePorId(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Advogado> buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(AdvogadoMapper::toDomain);
    }

    @Override
    public Optional<Advogado> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(AdvogadoMapper::toDomain);
    }

    @Override
    public List<Advogado> listarTodos() {
        return List.of();
    }
}

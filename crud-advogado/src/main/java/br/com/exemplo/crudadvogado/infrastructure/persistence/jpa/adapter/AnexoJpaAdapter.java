package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import br.com.exemplo.crudadvogado.core.domain.Anexo;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AnexoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.AnexoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.AnexoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AnexoJpaAdapter implements AnexoGateway {

    private final AnexoJpaRepository repository;
    private final AnexoMapper mapper;


    @PersistenceContext
    private EntityManager entityManager;


    public AnexoJpaAdapter(AnexoMapper mapper, AnexoJpaRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Anexo criar(Anexo domain) {
        AnexoEntity entity = mapper.toEntity(domain);
        AnexoEntity anexoSalvo = repository.save(entity);
        return mapper.toDomain(anexoSalvo);
    }
}



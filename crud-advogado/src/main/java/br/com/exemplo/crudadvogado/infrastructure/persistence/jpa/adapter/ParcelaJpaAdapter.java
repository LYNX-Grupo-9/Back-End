package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
import br.com.exemplo.crudadvogado.core.domain.Parcela;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ParcelaEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.ParcelaMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ParcelaJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ParcelaJpaAdapter implements ParcelaGateway {

    private final ParcelaJpaRepository repository;
    private final ParcelaMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ParcelaJpaAdapter(ParcelaMapper mapper, ParcelaJpaRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public Parcela criar(Parcela domain) {
        ParcelaEntity entity = mapper.toEntity(domain);
        ParcelaEntity parcelaSalva = repository.save(entity);
        return mapper.toDomain(parcelaSalva);
    }
}

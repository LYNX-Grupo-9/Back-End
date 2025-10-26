package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LancamentoGateway;
import br.com.exemplo.crudadvogado.core.domain.Lancamento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.LancamentoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.LancamentoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.LancamentoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LancamentoJpaAdapter implements LancamentoGateway {

    private final LancamentoJpaRepository repository;
    private final LancamentoMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public LancamentoJpaAdapter(LancamentoMapper mapper, LancamentoJpaRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public Lancamento criar(Lancamento domain) {
        LancamentoEntity entity = mapper.toEntity(domain);
        LancamentoEntity lancamentoSalvo = repository.save(entity);
        return mapper.toDomain(lancamentoSalvo);
    }
}

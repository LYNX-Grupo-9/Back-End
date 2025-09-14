package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.EventoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.EventoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.EventoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EventoJpaAdapter implements EventoGateway {

    private final EventoJpaRepository repository;
    private final EventoMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public EventoJpaAdapter(EventoMapper mapper, EventoJpaRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Evento criar(Evento domain) {
        EventoEntity entity = mapper.toEntity(domain);
        EventoEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}

package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LeadGateway;
import br.com.exemplo.crudadvogado.core.domain.Lead;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.LeadEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.LeadMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.LeadJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public class LeadJpaAdapter implements LeadGateway {

    private final LeadJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public LeadJpaAdapter(LeadJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lead criar(Lead domain) {
        LeadEntity entity = LeadMapper.toEntity(domain);

        UUID idAdvogado = domain.getAdvogado();
        if (idAdvogado != null) {
            AdvogadoEntity advogado = entityManager.find(AdvogadoEntity.class, idAdvogado);
            if (advogado == null) {
                throw new RuntimeException("Advogado não encontrado");
            }
            entity.setAdvogado(advogado);
        } else {
            throw new RuntimeException("Lead deve ter um advogado associado");
        }

        LeadEntity salvo = repository.save(entity);
        return LeadMapper.toDomain(salvo);
    }
}

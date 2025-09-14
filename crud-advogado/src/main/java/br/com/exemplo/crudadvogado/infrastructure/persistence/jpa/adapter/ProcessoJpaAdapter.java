package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ProcessoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.ProcessoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ProcessoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public class ProcessoJpaAdapter implements ProcessoGateway {

    private final ProcessoJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public ProcessoJpaAdapter(ProcessoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Processo criar(Processo domain) {
        ProcessoEntity entity = ProcessoMapper.toEntity(domain);

        UUID idAdvogado = domain.getIdAdvogado();
        if (idAdvogado != null) {
            AdvogadoEntity advogado = entityManager.find(AdvogadoEntity.class, idAdvogado);
            if (advogado == null) {
                throw new RuntimeException("Advogado não encontrado");
            }
            entity.setAdvogado(advogado);
        } else {
            throw new RuntimeException("Processo deve ter um advogado associado");
        }

        UUID idCliente = domain.getIdCliente();
        if (idCliente != null) {
            ClienteEntity cliente = entityManager.find(ClienteEntity.class, idCliente);
            if (cliente == null) {
                throw new RuntimeException("Cliente não encontrado");
            }
            entity.setCliente(cliente);
        } else {
            throw new RuntimeException("Processo deve ter um advogado associado");
        }

        ProcessoEntity salvo = repository.save(entity);
        return ProcessoMapper.toDomain(salvo);
    }

}

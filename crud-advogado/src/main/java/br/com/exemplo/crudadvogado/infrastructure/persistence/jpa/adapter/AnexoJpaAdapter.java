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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public List<Anexo> buscarPorIdCliente(UUID idCliente) {
        List<AnexoEntity> entities = repository.findByCliente_IdCliente(idCliente);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Anexo> buscarPorIdProcesso(UUID idProcesso) {
        List<AnexoEntity> entities = repository.findByProcesso_IdProcesso(idProcesso);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorIdItem(String idItem) {
        return repository.existsByIdItem(idItem);
    }

    @Override
    public void deletarPorIdItem(String idItem) {
        repository.deleteByIdItem(idItem);
    }

    @Override
    public List<Anexo> buscarPorId(Long idAnexo) {
        List<AnexoEntity> entities = repository.findByIdAnexo(idAnexo);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }


}



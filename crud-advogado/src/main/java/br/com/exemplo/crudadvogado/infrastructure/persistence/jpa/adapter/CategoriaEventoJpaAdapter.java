package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.CategoriaEventoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.CategoriaEventoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.CategoriaEventoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class CategoriaEventoJpaAdapter implements CategoriaEventoGateway {

    private final CategoriaEventoJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public CategoriaEventoJpaAdapter(CategoriaEventoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoriaEvento criar(CategoriaEvento domain) {
        CategoriaEventoEntity entity = CategoriaEventoMapper.toEntity(domain);

        UUID idAdvogado = domain.getIdAdvogado();
        if (idAdvogado != null) {
            AdvogadoEntity advogado = entityManager.find(AdvogadoEntity.class, idAdvogado);
            if (advogado == null) {
                throw new RuntimeException("Advogado não encontrado com ID: " + idAdvogado);
            }
            entity.setAdvogado(advogado);
        }

        CategoriaEventoEntity salvo = repository.save(entity);
        return CategoriaEventoMapper.toDomain(salvo);
    }

    @Override
    public List<CategoriaEvento> buscarTodas() {
        List<CategoriaEventoEntity> entities = repository.findAll();
        return entities.stream()
                .map(CategoriaEventoMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<CategoriaEvento> buscarPorNome(String nomeEvento) {
        return Optional.empty();
    }

    @Override
    public List<CategoriaEvento> buscarPorAdvogadoId(UUID idAdvogado) {
        List<CategoriaEventoEntity> entities = repository.findByAdvogadoIdAdvogado(idAdvogado);
        return entities.stream()
                .map(CategoriaEventoMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<CategoriaEvento> buscarPorId(Long idCategoria) {
        return repository.findById(idCategoria)
                .map(CategoriaEventoMapper::toDomain);
    }

    @Override
    public List<Object[]> contarCategoriasAgrupadasPorNome(Long idAdvogado) {
        return List.of();
    }

    @Override
    public void deletar(Long idCategoria) {
        repository.deleteById(idCategoria);
    }

    @Override
    public CategoriaEvento atualizar(CategoriaEvento categoriaEvento) {
        CategoriaEventoEntity entity = CategoriaEventoMapper.toEntity(categoriaEvento);

        if (categoriaEvento.getIdAdvogado() != null) {
            UUID idAdvogado = categoriaEvento.getIdAdvogado();
            AdvogadoEntity advogado = entityManager.find(AdvogadoEntity.class, idAdvogado);
            if (advogado == null) {
                throw new RuntimeException("Advogado não encontrado com ID: " + idAdvogado);
            }
            entity.setAdvogado(advogado);
        }

        CategoriaEventoEntity entityAtualizada = repository.save(entity);
        return CategoriaEventoMapper.toDomain(entityAtualizada);
    }

    @Override
    public List<Object[]> contarCategoriasAgrupadasPorNome(Long idAdvogado) {
        return repository.countCategoriasAgrupadasPorNome(idAdvogado);
    }
}

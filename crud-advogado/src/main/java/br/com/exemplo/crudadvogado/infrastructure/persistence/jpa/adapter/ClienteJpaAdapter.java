package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.ClienteMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ClienteJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class ClienteJpaAdapter implements ClienteGateway {

    private final ClienteJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public ClienteJpaAdapter(ClienteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente criar(Cliente domain) {
        ClienteEntity entity = ClienteMapper.toEntity(domain);

        UUID idAdvogado = domain.getIdAdvogado();
        if (idAdvogado != null) {
            AdvogadoEntity advogado = entityManager.find(AdvogadoEntity.class, idAdvogado);
            if (advogado == null) {
                throw new RuntimeException("Advogado não encontrado");
            }
            entity.setAdvogado(advogado);
        } else {
            throw new RuntimeException("Cliente deve ter um advogado associado");
        }

        ClienteEntity salvo = repository.save(entity);
        return ClienteMapper.toDomain(salvo);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return null;
    }

    @Override
    public List<Cliente> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }

    @Override
    public List<Cliente> buscarPorAdvogado(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogado(idAdvogado)
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Long contarPorAdvogado(UUID idAdvogado) {
        return repository.countByAdvogadoIdAdvogado(idAdvogado);
    }

    @Override
    public List<Cliente> buscarPorAdvogadoOrdenadoPorNome(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogadoOrderByNaturalidadeAsc(idAdvogado)
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }

    @Override
    public List<Cliente> buscarPorAdvogadoOrdenadoPorNaturalidade(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogadoOrderByNomeAsc(idAdvogado)
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }

    @Override
    public List<Cliente> buscarPorAdvogadoOrdenadoPorDataNascimento(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogadoOrderByDataNascimentoAsc(idAdvogado)
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }

    @Override
    public List<Cliente> buscarPorTermo(String termo, UUID idAdvogado) {
        return repository.buscarPorNomeEmailTelefonePorAdvogado(termo, idAdvogado)
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }

}

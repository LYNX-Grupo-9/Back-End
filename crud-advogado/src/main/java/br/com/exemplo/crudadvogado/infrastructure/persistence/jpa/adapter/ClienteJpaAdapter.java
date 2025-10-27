package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.usecase.cliente.ListarClientesOrdenadosPorProcessosUseCase;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.ClienteMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ClienteJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Cliente salvar(Cliente domain) {
        ClienteEntity entityExistente = repository.findById(domain.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para atualização"));

        atualizarEntidadeComDomain(entityExistente, domain);

        if (domain.getIdAdvogado() != null &&
                !domain.getIdAdvogado().equals(entityExistente.getAdvogado().getIdAdvogado())) {
            AdvogadoEntity advogado = entityManager.find(AdvogadoEntity.class, domain.getIdAdvogado());
            if (advogado == null) {
                throw new RuntimeException("Advogado não encontrado");
            }
            entityExistente.setAdvogado(advogado);
        }

        ClienteEntity atualizado = repository.save(entityExistente);
        return ClienteMapper.toDomain(atualizado);
    }

    @Override
    public Long contarPorAdvogadoId(UUID idAdvogado) {
        return repository.countByAdvogadoIdAdvogado(idAdvogado);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(ClienteMapper::toDomain);
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

    @Override
    public List<Cliente> buscarClientesComQuantidadeProcessos(UUID idAdvogado) {
        List<Object[]> resultados = repository.findClientesComQuantidadeProcessos(idAdvogado);

        return resultados.stream()
                .map(resultado -> {
                    ClienteEntity clienteEntity = (ClienteEntity) resultado[0];
                    Long qtdProcessos = (Long) resultado[1];

                    Cliente cliente = ClienteMapper.toDomain(clienteEntity);
                    return cliente;
                })
                .toList();
    }

    @Override
    public Page<Cliente> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(ClienteMapper::toDomain);
    }


    private void atualizarEntidadeComDomain(ClienteEntity entity, Cliente domain) {
        if (domain.getNome() != null) entity.setNome(domain.getNome());
        if (domain.getDocumento() != null) entity.setDocumento(domain.getDocumento());
        if (domain.getEmail() != null) entity.setEmail(domain.getEmail().getEnderacoEmail());
        if (domain.getTelefone() != null) entity.setTelefone(domain.getTelefone());
        if (domain.getEndereco() != null) entity.setEndereco(domain.getEndereco());
        if (domain.getEstadoCivil() != null) entity.setEstadoCivil(domain.getEstadoCivil().getValor());
        if (domain.getGenero() != null) entity.setGenero(domain.getGenero().getValor());
        if (domain.getProfissao() != null) entity.setProfissao(domain.getProfissao());
        if (domain.getPassaporte() != null) entity.setPassaporte(domain.getPassaporte());
        if (domain.getCnh() != null) entity.setCnh(domain.getCnh());
        if (domain.getNaturalidade() != null) entity.setNaturalidade(domain.getNaturalidade());
        if (domain.getDataNascimento() != null) entity.setDataNascimento(domain.getDataNascimento());
    }
}

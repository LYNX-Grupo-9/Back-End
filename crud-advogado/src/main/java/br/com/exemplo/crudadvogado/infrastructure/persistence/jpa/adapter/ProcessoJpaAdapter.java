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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public Long contarProcessosPorCliente(UUID clienteId) {
        return repository.countByClienteIdCliente(clienteId);
    }

    @Override
    public List<Processo> listarPorIdAdvogado(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogado(idAdvogado)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Processo> buscarPorId(UUID idProcesso) {
        return repository.findById(idProcesso)
                .map(ProcessoMapper::toDomain);
    }

    @Override
    public List<Processo> listarPorIdCliente(UUID idCliente) {
        return repository.findByClienteIdCliente(idCliente)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void excluirPorId(UUID idProcesso) {
        repository.deleteById(idProcesso);
    }

    @Override
    public boolean existePorId(UUID idProcesso) {
        return repository.existsById(idProcesso);
    }

    @Override
    public List<Processo> listarProcessosAtivosPorAdvogado(UUID idAdvogado) {
        return repository.findByStatusNotIgnoreCaseAndAdvogadoIdAdvogado("Arquivado", idAdvogado)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> contarProcessosPorStatusPorAdvogado(UUID idAdvogado) {
        List<Processo> processos = repository.findByAdvogadoIdAdvogado(idAdvogado)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());

        return processos.stream()
                .collect(Collectors.groupingBy(
                        Processo::getStatus,
                        Collectors.counting()
                ));
    }

    @Override
    public List<Processo> buscarPorNumeroTituloOuCliente(String termo, UUID idAdvogado) {
        return repository.buscarPorNumeroTituloOuCliente(termo, idAdvogado)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Processo> listarProcessosOrdenadosPorStatus(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogadoOrderByStatusAsc(idAdvogado)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Double calcularValorMedioPorAdvogado(UUID idAdvogado) {
        return repository.calcularValorMedioPorAdvogado(idAdvogado);
    }

    @Override
    public List<Processo> listarProcessosOrdenadosPorValor(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogadoOrderByValorDesc(idAdvogado)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());
    }
}

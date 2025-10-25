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

    @Override
    public Optional<Processo> buscarPorNumeroProcesso(String numeroProcesso) {
        return repository.findByNumeroProcesso(numeroProcesso)
                .map(ProcessoMapper::toDomain);
    }

    @Override
    public Map<String, Long> contarProcessosPorClasseProcessualPorAdvogado(UUID idAdvogado) {
        List<Object[]> resultados = repository.contarProcessosPorClasseProcessualPorAdvogado(idAdvogado);

        return resultados.stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0],
                        r -> (Long) r[1]
                ));
    }

    @Override
    public List<Processo> listarProcessosOrdenadosPorNomeCliente(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogadoOrderByClienteNomeAsc(idAdvogado)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Processo> listarProcessosOrdenadosPorNumeroProcesso(UUID idAdvogado) {
        return repository.findByAdvogadoIdAdvogadoOrderByNumeroProcessoAsc(idAdvogado)
                .stream()
                .map(ProcessoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Processo atualizar(Processo domain) {
        if (domain.getIdProcesso() == null) {
            throw new RuntimeException("Processo deve ter ID para atualização");
        }

        ProcessoEntity entity = repository.findById(domain.getIdProcesso())
                .orElseThrow(() -> new RuntimeException("Processo não encontrado com ID: " + domain.getIdProcesso()));

        atualizarEntity(entity, domain);

        if (domain.getIdAdvogado() != null) {
            AdvogadoEntity advogado = entityManager.find(AdvogadoEntity.class, domain.getIdAdvogado());
            if (advogado == null) {
                throw new RuntimeException("Advogado não encontrado");
            }
            entity.setAdvogado(advogado);
        }

        if (domain.getIdCliente() != null) {
            ClienteEntity cliente = entityManager.find(ClienteEntity.class, domain.getIdCliente());
            if (cliente == null) {
                throw new RuntimeException("Cliente não encontrado");
            }
            entity.setCliente(cliente);
        }

        ProcessoEntity atualizado = repository.save(entity);
        return ProcessoMapper.toDomain(atualizado);
    }

    private void atualizarEntity(ProcessoEntity entity, Processo domain) {
        if (domain.getTitulo() != null) {
            entity.setTitulo(domain.getTitulo());
        }
        if (domain.getNumeroProcesso() != null) {
            entity.setNumeroProcesso(domain.getNumeroProcesso());
        }
        if (domain.getDescricao() != null) {
            entity.setDescricao(domain.getDescricao());
        }
        if (domain.getStatus() != null) {
            entity.setStatus(domain.getStatus());
        }
        if (domain.getClasseProcessual() != null) {
            entity.setClasseProcessual(domain.getClasseProcessual());
        }
        if (domain.getAssunto() != null) {
            entity.setAssunto(domain.getAssunto());
        }
        if (domain.getTribunal() != null) {
            entity.setTribunal(domain.getTribunal());
        }
        if (domain.getValor() != null) {
            entity.setValor(domain.getValor());
        }
        if (domain.getAutor() != null) {
            entity.setAutor(domain.getAutor());
        }
        if (domain.getAdvRequerente() != null) {
            entity.setAdvRequerente(domain.getAdvRequerente());
        }
        if (domain.getReu() != null) {
            entity.setReu(domain.getReu());
        }
        if (domain.getAdvReu() != null) {
            entity.setAdvReu(domain.getAdvReu());
        }
    }
}

package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LancamentoGateway;
import br.com.exemplo.crudadvogado.core.domain.Lancamento;
import br.com.exemplo.crudadvogado.core.domain.Parcela;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.LancamentoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.LancamentoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.ParcelaMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.LancamentoJpaRepository;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ParcelaJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional
public class LancamentoJpaAdapter implements LancamentoGateway {

    private final LancamentoJpaRepository repository;
    private final ParcelaJpaRepository parcelaRepository;
    private final LancamentoMapper mapper;
    private final ParcelaMapper parcelaMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public LancamentoJpaAdapter(LancamentoJpaRepository repository,
                                ParcelaJpaRepository parcelaRepository,
                                LancamentoMapper mapper,
                                ParcelaMapper parcelaMapper) {
        this.repository = repository;
        this.parcelaRepository = parcelaRepository;
        this.mapper = mapper;
        this.parcelaMapper = parcelaMapper;
    }

    @Override
    public Lancamento criar(Lancamento domain) {
        LancamentoEntity entity = mapper.toEntity(domain);
        LancamentoEntity lancamentoSalvo = repository.save(entity);
        return mapper.toDomain(lancamentoSalvo);
    }

    @Override
    public List<Lancamento> buscarPorProcessos(List<UUID> idsProcessos) {
        if (idsProcessos == null || idsProcessos.isEmpty()) {
            return List.of();
        }

        return repository.findByProcesso_IdProcessoIn(idsProcessos).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Parcela> buscarParcelasPorLancamento(Long idLancamento) {
        return parcelaRepository.findByIdLancamento(idLancamento).stream()
                .map(parcelaMapper::toDomain)
                .collect(Collectors.toList());
    }
}

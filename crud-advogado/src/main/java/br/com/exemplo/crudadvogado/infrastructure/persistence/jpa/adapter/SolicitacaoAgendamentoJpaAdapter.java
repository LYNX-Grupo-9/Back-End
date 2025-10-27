package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.SolicitacaoAgendamentoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.SolicitacaoAgendamentoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.SolicitacaoAgendamentoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional
public class SolicitacaoAgendamentoJpaAdapter implements SolicitacaoAgendamentoGateway {

    private final SolicitacaoAgendamentoJpaRepository solicitacaoAgendamentoJpaRepository;
    private final SolicitacaoAgendamentoMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public SolicitacaoAgendamentoJpaAdapter(SolicitacaoAgendamentoJpaRepository solicitacaoAgendamentoJpaRepository, SolicitacaoAgendamentoMapper mapper) {
        this.solicitacaoAgendamentoJpaRepository = solicitacaoAgendamentoJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SolicitacaoAgendamento criar(SolicitacaoAgendamento domain){
        SolicitacaoAgendamentoEntity entity = mapper.toEntity(domain);
        SolicitacaoAgendamentoEntity salvo = solicitacaoAgendamentoJpaRepository.save(entity);
        return mapper.toDomain(salvo);
    }

    @Override
    public Optional<SolicitacaoAgendamento> buscarPorId(Long id) {
        return solicitacaoAgendamentoJpaRepository.findByIdSolicitacaoAgendamento(id)
                .map(mapper::toDomain);
    }

    @Override
    public SolicitacaoAgendamento atualizar(SolicitacaoAgendamento solicitacao) {
        var entity = mapper.toEntity(solicitacao);
        var savedEntity = solicitacaoAgendamentoJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<SolicitacaoAgendamento> buscarPorAdvogado(UUID idAdvogado) {
        return solicitacaoAgendamentoJpaRepository.findByAdvogadoIdAdvogado(idAdvogado)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}

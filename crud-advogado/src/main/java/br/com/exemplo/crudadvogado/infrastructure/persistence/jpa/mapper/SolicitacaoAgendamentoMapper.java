package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.SolicitacaoAgendamentoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.AdvogadoJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoAgendamentoMapper {

    private final AdvogadoJpaRepository advogadoJpaRepository;

    public SolicitacaoAgendamentoMapper(AdvogadoJpaRepository advogadoJpaRepository) {
        this.advogadoJpaRepository = advogadoJpaRepository;
    }

    public SolicitacaoAgendamentoEntity toEntity(SolicitacaoAgendamento domain) {
        if (domain == null) {
            return null;
        }
        SolicitacaoAgendamentoEntity entity = new SolicitacaoAgendamentoEntity();
        entity.setIdSolicitacaoAgendamento(domain.getIdSolicitacaoAgendamento());
        entity.setNome(domain.getNome());
        entity.setEmail(domain.getEmail());
        entity.setTelefone(domain.getTelefone());
        entity.setAssunto(domain.getAssunto());
        entity.setDataSolicitacao(domain.getDataSolicitacao());
        entity.setHoraSolicitacao(domain.getHoraSolicitacao());
        entity.setVisualizado(domain.getVisualizado());

        AdvogadoEntity advogado = advogadoJpaRepository.findById(domain.getIdAdvogado())
                .orElseThrow(() -> new EntityNotFoundException("Advogado não encontrado"));
        entity.setAdvogado(advogado);

        return entity;
    }

    public SolicitacaoAgendamento toDomain(SolicitacaoAgendamentoEntity entity) {
        if (entity == null) {
            return null;
        }

        var domain = SolicitacaoAgendamento.criarExistente(
                entity.getIdSolicitacaoAgendamento(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getAssunto(),
                entity.getDataSolicitacao(),
                entity.getHoraSolicitacao(),
                entity.getAdvogado().getIdAdvogado(),
                entity.getVisualizado()
        );

        return domain;
    }

}

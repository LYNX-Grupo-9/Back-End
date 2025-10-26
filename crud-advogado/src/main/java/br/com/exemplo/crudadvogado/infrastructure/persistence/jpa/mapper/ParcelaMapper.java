package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Parcela;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.parcela.Status;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.LancamentoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ParcelaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class ParcelaMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public ParcelaEntity toEntity(Parcela domain){
        if(domain == null){
            return null;
        }

        var entity = new ParcelaEntity();
        entity.setIdParcela(domain.getIdParcela());
        entity.setValor(domain.getValor());
        entity.setDataVencimento(domain.getDataVencimento());
        entity.setStatus(domain.getStatus().getValor());

        if(domain.getIdLancamento() != null) {
            LancamentoEntity lancamento = entityManager.getReference(LancamentoEntity.class, domain.getIdLancamento());
            entity.setLancamento(lancamento);
        }

        return entity;
    }

    public Parcela toDomain(ParcelaEntity entity){
        if(entity == null){
            return null;
        }

        Long idLancamento = entity.getLancamento() != null
                ? entity.getLancamento().getIdLancamento()
                : null;

        Parcela domain = new Parcela(
                entity.getValor(),
                entity.getDataVencimento(),
                Status.criar(entity.getStatus()),
                idLancamento
        );
        domain.setIdParcela(entity.getIdParcela());

        return domain;
    }
}

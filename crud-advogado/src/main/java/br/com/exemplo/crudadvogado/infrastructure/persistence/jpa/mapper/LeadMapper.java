package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Lead;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.LeadEntity;
import org.springframework.stereotype.Component;

@Component
public class LeadMapper {

    public static LeadEntity toEntity(Lead domain) {
        if (domain == null) {
            return null;
        }

        var entity = new LeadEntity();
        entity.setIdLead(domain.getIdLead());
        entity.setNome(domain.getNome());
        entity.setTelefone(domain.getTelefone());
        entity.setEmail(domain.getEmail().getEnderacoEmail());
        entity.setAssunto(domain.getAssunto());
        entity.setMensagem(domain.getMensagem());

        return entity;
    }

    public static Lead toDomain(LeadEntity entity) {
        if (entity == null) {
            return null;
        }

        var domain = Lead.criarNovoLead(
                entity.getNome(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getAssunto(),
                entity.getMensagem(),
                entity.getAdvogado().getIdAdvogado()
        );

        return domain;
    }
}

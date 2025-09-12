package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.CategoriaEventoEntity;

public class CategoriaEventoMapper {

    public static CategoriaEventoEntity toEntity(CategoriaEvento domain) {
        if (domain == null) {
            return null;
        }

        var entity = new CategoriaEventoEntity();
        entity.setId(domain.getIdCategoria());
        entity.setNome(domain.getNome());
        entity.setCor(domain.getCor());

        return entity;
    }

    public static CategoriaEvento toDomain(CategoriaEventoEntity entity) {
        if (entity == null) {
            return null;
        }

        var domain = CategoriaEvento.criarNovo(
                entity.getNome(),
                entity.getCor(),
                entity.getAdvogado().getIdAdvogado()
        );

        return domain;
    }

}

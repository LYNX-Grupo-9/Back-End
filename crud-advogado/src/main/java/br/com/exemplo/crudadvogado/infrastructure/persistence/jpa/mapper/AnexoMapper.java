package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Anexo;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AnexoEntity;

import java.util.Objects;

public class AnexoMapper {

    public static AnexoEntity toEntity(Anexo domain) {
        if(Objects.isNull(domain)){
            return null;
        }

        return new AnexoEntity(
                domain.getIdAnexo(),
                domain.getNomeAnexo(),
                domain.getIdCliente(),
                domain.getIdProcesso()
        );
    }

    public static Anexo toDomain(AnexoEntity entity) {
        if(Objects.isNull(entity)){
            return null;
        }

        return Anexo.criarNovo(
                entity.getIdItem(),
                entity.getNomeAnexo(),
                entity.getCliente().getIdCliente(),
                entity.getProcesso().getIdProcesso()
        );
    }
}

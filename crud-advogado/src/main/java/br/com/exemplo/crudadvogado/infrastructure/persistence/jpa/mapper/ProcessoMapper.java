package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Processo;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ProcessoEntity;

import java.util.List;

public class ProcessoMapper {

    public static ProcessoEntity toEntity(Processo domain){
        if(domain == null){
            return null;
        }

        var entity = new ProcessoEntity();
        entity.setIdProcesso(domain.getIdProcesso());
        entity.setTitulo(domain.getTitulo());
        entity.setNumeroProcesso(domain.getNumeroProcesso());
        entity.setDescricao(domain.getDescricao());
        entity.setStatus(domain.getStatus());
        entity.setClasseProcessual(domain.getClasseProcessual());
        entity.setAssunto(domain.getAssunto());
        entity.setTribunal(domain.getTribunal());
        entity.setValor(domain.getValor());
        entity.setAutor(domain.getAutor());
        entity.setAdvRequerente(domain.getAdvRequerente());
        entity.setReu(domain.getReu());
        entity.setAdvReu(domain.getAdvReu());

        return entity;
    }

    public static Processo toDomain(ProcessoEntity entity){
        if(entity == null){
            return null;
        }

        var domain = Processo.criarExistente(
                entity.getIdProcesso(), // ← ADICIONAR COMO PRIMEIRO PARÂMETRO
                entity.getTitulo(),
                entity.getNumeroProcesso(),
                entity.getDescricao(),
                entity.getStatus(),
                entity.getClasseProcessual(),
                entity.getAssunto(),
                entity.getTribunal(),
                entity.getValor(),
                entity.getAutor(),
                entity.getAdvRequerente(),
                entity.getReu(),
                entity.getAdvReu(),
                entity.getAdvogado().getIdAdvogado(),
                entity.getCliente().getIdCliente(),
                List.of()
        );

        return domain;
    }

}

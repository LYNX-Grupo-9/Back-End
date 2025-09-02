package br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.mapper;



import br.com.exemplo.crudadvogado.core.application.dto.command.AdvogadoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.AdvogadoLoginCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.AdvogadoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.AdvogadoToken;
import br.com.exemplo.crudadvogado.core.domain.Advogado;
import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.entity.AdvogadoEntity;

import java.util.Objects;

public class AdvogadoMapper {

    public static AdvogadoEntity toEntity(Advogado domain) {
        if(Objects.isNull(domain)) {
            return null;
        }

        var entity = new AdvogadoEntity();
        entity.setIdAdvogado(domain.getIdAdvogado());
        entity.setNome(domain.getNome());
        entity.setRegistroOab(domain.getOab().getNumeroOab());
        entity.setCpf(domain.getCpf().getNumeroCpf());
        entity.setEmail(domain.getEmail().getEnderacoEmail());
        entity.setSenha(domain.getSenha().getValor());

        return entity;
    }

    public static Advogado toDomain(AdvogadoEntity entity) {
        if(Objects.isNull(entity)) {
            return null;
        }

        var domain = Advogado.criarNovo(
                entity.getNome(),
                entity.getRegistroOab(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getSenha()
        );

        return domain;
    }

    public static AdvogadoEntity of (AdvogadoCommand command) {

        return new AdvogadoEntity(
                command.nome(),
                command.oab(),
                command.cpf(),
                command.email(),
                command.senha()
        );
    }

//    public static Advogado of (AdvogadoLoginCommand advogadoLogin) {
//        if (advogadoLogin == null) {
//            return null;
//        }
//
//        return new Advogado(
//                advogadoLogin.email(),
//                advogadoLogin.senha()
//        );
//    }

    public static AdvogadoToken of(AdvogadoEntity advogadoEntity, String token) {
        if(Objects.isNull(advogadoEntity)) {
            return null;
        }

        return new AdvogadoToken(
                advogadoEntity.getIdAdvogado(),
                advogadoEntity.getNome(),
                advogadoEntity.getEmail(),
                token
        );
    }

//    public static AdvogadoResponse of (Advogado advogado) {
//        AdvogadoResponse advogadoResponse = new AdvogadoResponse();
//
//        advogadoResponse.setIdAdvogado(advogado.getIdAdvogado());
//        advogadoResponse.setNome(advogado.getNome());
//        advogadoResponse.setEmail(advogado.getEmail());
//        advogadoResponse.setRegistroOab(advogado.getRegistroOab());
//
//        return advogadoResponse;
//    }

}

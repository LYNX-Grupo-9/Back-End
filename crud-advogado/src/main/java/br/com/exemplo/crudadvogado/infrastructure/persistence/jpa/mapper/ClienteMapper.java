package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;

public class ClienteMapper {

    public static ClienteEntity toEntity(Cliente domain) {
        if(domain == null) {
            return null;
        }

        var entity = new ClienteEntity();
        entity.setIdCliente(domain.getIdCliente());
        entity.setNome(domain.getNome());
        entity.setDocumento(domain.getDocumento());
        entity.setTipoDocumento(domain.getTipoDocumento());
        entity.setEmail(domain.getEmail().getEnderacoEmail());
        entity.setTelefone(domain.getTelefone());
        entity.setEndereco(domain.getEndereco());
        entity.setGenero(domain.getGenero().getValor());
        entity.setDataNascimento(domain.getDataNascimento());
        entity.setEstadoCivil(domain.getEstadoCivil().getValor());
        entity.setProfissao(domain.getProfissao());
        entity.setPassaporte(domain.getPassaporte());
        entity.setCnh(domain.getCnh());
        entity.setNaturalidade(domain.getNaturalidade());

        return entity;
    }

    public static Cliente toDomain(ClienteEntity entity) {
        if(entity == null) {
            return null;
        }

        var domain = Cliente.criarNovo(
                entity.getNome(),
                entity.getDocumento(),
                entity.getTipoDocumento(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getEndereco(),
                entity.getGenero(),
                entity.getDataNascimento(),
                entity.getEstadoCivil(),
                entity.getProfissao(),
                entity.getPassaporte(),
                entity.getCnh(),
                entity.getNaturalidade(),
                entity.getAdvogado().getIdAdvogado()
        );

        return domain;
    }

}

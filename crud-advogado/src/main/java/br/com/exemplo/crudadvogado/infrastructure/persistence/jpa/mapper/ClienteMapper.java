package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        // Extrai IDs dos processos
        List<UUID> processosIds = entity.getProcessos().stream()
                .map(processo -> processo.getIdProcesso())
                .collect(Collectors.toList());

        // Extrai IDs dos eventos
        List<Long> eventosIds = entity.getEventos().stream()
                .map(evento -> evento.getIdEvento())
                .collect(Collectors.toList());

        // Cria o cliente com todas as propriedades, incluindo as listas
        Cliente cliente = new Cliente(
                entity.getIdCliente(),
                entity.getNome(),
                entity.getDocumento(),
                entity.getTipoDocumento(),
                Email.criar(entity.getEmail()),
                entity.getTelefone(),
                entity.getEndereco(),
                Genero.criar(entity.getGenero()),
                entity.getDataNascimento(),
                EstadoCivil.criar(entity.getEstadoCivil()),
                entity.getProfissao(),
                entity.getPassaporte(),
                entity.getCnh(),
                entity.getNaturalidade(),
                entity.getQtdProcessos(),
                entity.getAdvogado().getIdAdvogado(),
                processosIds, // Lista de IDs dos processos
                List.of(),    // Anexos (ajuste se necessário)
                eventosIds    // Lista de IDs dos eventos
        );

        return cliente;
    }

}

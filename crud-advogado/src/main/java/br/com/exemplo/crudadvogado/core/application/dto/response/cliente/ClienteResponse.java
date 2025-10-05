package br.com.exemplo.crudadvogado.core.application.dto.response.cliente;

import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

public record ClienteResponse(
        UUID idCliente,
        String nome,
        String documento,
        String tipoDocumento,
        String email,
        String telefone,
        String endereco,
        String genero,
        Date dataNascimento,
        String estadoCivil,
        String profissao,
        String passaporte,
        String cnh,
        String naturalidade,
        UUID idAdvogado,
        Long qtdProcessos
) {
    public static ClienteResponse fromDomain(Cliente cliente) {
        return new ClienteResponse(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getDocumento(),
                cliente.getTipoDocumento(),
                cliente.getEmail().getEnderacoEmail(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getGenero().getValor(),
                cliente.getDataNascimento(),
                cliente.getEstadoCivil().getValor(),
                cliente.getProfissao(),
                cliente.getPassaporte(),
                cliente.getCnh(),
                cliente.getNaturalidade(),
                cliente.getIdAdvogado(),
                cliente.getQtdProcessos()
        );
    }
}

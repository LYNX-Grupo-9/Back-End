package br.com.exemplo.crudadvogado.core.application.dto.command.cliente;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero;

import java.util.Date;

public record AtualizarClienteCommand(
        String nome,
        String documento,
        String email,
        String telefone,
        String endereco,
        Genero genero,
        Date dataNascimento,
        EstadoCivil estadoCivil,
        String profissao,
        String passaporte,
        String cnh,
        String naturalidade
) {
}

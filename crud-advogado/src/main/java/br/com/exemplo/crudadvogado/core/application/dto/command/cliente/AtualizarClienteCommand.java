package br.com.exemplo.crudadvogado.core.application.dto.command.cliente;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record AtualizarClienteCommand(
        String nome,
        String documento,
        Email email,
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

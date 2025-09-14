package br.com.exemplo.crudadvogado.core.application.dto.command.cliente;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record CriarClienteCommand(
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
        UUID idAdvogado
) {
}

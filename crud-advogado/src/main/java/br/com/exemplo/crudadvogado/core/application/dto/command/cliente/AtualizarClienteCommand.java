package br.com.exemplo.crudadvogado.core.application.dto.command.cliente;

import java.time.LocalDate;
import java.util.UUID;

public record AtualizarClienteCommand(
        String nome,
        String documento,
        String email,
        String telefone,
        String endereco,
        String genero,
        LocalDate dataNascimento,
        String estadoCivil,
        String profissao,
        String passaporte,
        String cnh,
        String naturalidade,
        UUID advogadoId
) {
}

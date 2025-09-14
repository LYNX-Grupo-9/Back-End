package br.com.exemplo.crudadvogado.core.application.dto.response.cliente;

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
        UUID advogadoResponsavel,
        Long qtdProcessos
) {
}

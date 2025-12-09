package br.com.exemplo.crudadvogado.core.application.dto.response.cliente;

import java.util.Date;
import java.util.UUID;

public record CriarClienteResponse(
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
        Long qtdProcessos,
        UUID advogadoResponsavel
) {
    public UUID idAdvogado() {
        return null;
    }
}

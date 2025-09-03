package br.com.exemplo.crudadvogado.core.application.dto.command.advogado;

public record AdvogadoCommand(
        String nome,
        String oab,
        String cpf,
        String email,
        String senha
) {
}

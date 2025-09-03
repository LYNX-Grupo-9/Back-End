package br.com.exemplo.crudadvogado.core.application.dto.command.advogado;

public record AdvogadoLoginCommand(
        String email,
        String senha
) {
}

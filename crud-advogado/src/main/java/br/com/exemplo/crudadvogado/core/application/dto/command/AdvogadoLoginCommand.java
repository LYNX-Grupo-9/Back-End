package br.com.exemplo.crudadvogado.core.application.dto.command;

public record AdvogadoLoginCommand(
        String email,
        String senha
) {
}

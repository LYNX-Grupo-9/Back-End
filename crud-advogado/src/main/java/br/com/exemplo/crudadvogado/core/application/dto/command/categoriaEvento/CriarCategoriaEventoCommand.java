package br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento;

import java.util.UUID;

public record CriarCategoriaEventoCommand(
        String nome,
        String cor,
        UUID idAdvogado
) {
}

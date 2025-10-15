package br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento;

public record AtualizarCategoriaCommand(
        String nomeEvento,
        String cor
) {}

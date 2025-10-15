package br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento;

public record CategoriaEventoResponse(
        Long idCategoria,
        String nome,
        String cor
) {}
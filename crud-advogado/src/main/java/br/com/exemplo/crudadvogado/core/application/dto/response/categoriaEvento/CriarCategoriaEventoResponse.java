package br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento;

public record CriarCategoriaEventoResponse(
        Long idCategoria,
        String nome,
        String cor
) {
}

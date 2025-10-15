package br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento;

import java.util.Map;

public record ContarCategoriasResponse(
        Map<String, CategoriaDetalhesResponse> categorias
) {}

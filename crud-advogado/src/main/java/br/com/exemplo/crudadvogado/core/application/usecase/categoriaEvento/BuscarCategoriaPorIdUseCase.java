package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.CategoriaEventoNotFoundException;

public class BuscarCategoriaPorIdUseCase {
    private final CategoriaEventoGateway categoriaEventoGateway;

    public BuscarCategoriaPorIdUseCase(CategoriaEventoGateway categoriaEventoGateway) {
        this.categoriaEventoGateway = categoriaEventoGateway;
    }

    public CategoriaEventoResponse executar(Long idCategoria) {
        return categoriaEventoGateway.buscarPorId(idCategoria)
                .map(categoria -> new CategoriaEventoResponse(
                        categoria.getIdCategoria(),
                        categoria.getNome(),
                        categoria.getCor()
                ))
                .orElseThrow(() -> new CategoriaEventoNotFoundException("Categoria não encontrada com ID: " + idCategoria));
    }
}

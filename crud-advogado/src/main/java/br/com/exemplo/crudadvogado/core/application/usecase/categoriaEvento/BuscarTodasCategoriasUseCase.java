package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaEventoResponse;

import java.util.List;

public class BuscarTodasCategoriasUseCase {
    private final CategoriaEventoGateway categoriaEventoGateway;

    public BuscarTodasCategoriasUseCase(CategoriaEventoGateway categoriaEventoGateway) {
        this.categoriaEventoGateway = categoriaEventoGateway;
    }

    public List<CategoriaEventoResponse> executar() {
        return categoriaEventoGateway.buscarTodas()
                .stream()
                .map(categoria -> new CategoriaEventoResponse(
                        categoria.getIdCategoria(),
                        categoria.getNome(),
                        categoria.getCor()
                ))
                .toList();
    }
}

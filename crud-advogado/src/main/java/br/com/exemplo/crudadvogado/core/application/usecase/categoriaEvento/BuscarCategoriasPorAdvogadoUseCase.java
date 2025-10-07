package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaEventoResponse;

import java.util.List;
import java.util.UUID;

public class BuscarCategoriasPorAdvogadoUseCase {
    private final CategoriaEventoGateway categoriaEventoGateway;

    public BuscarCategoriasPorAdvogadoUseCase(CategoriaEventoGateway categoriaEventoGateway) {
        this.categoriaEventoGateway = categoriaEventoGateway;
    }

    public List<CategoriaEventoResponse> executar(UUID idAdvogado) {
        return categoriaEventoGateway.buscarPorAdvogadoId(idAdvogado)
                .stream()
                .map(categoria -> new CategoriaEventoResponse(
                        categoria.getIdCategoria(),
                        categoria.getNome(),
                        categoria.getCor()
                ))
                .toList();
    }
}

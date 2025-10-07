package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.exception.CategoriaEventoNotFoundException;

public class DeletarCategoriaUseCase {
    private final CategoriaEventoGateway categoriaEventoGateway;
    private final EventoGateway eventoGateway;

    public DeletarCategoriaUseCase(CategoriaEventoGateway categoriaEventoGateway, EventoGateway eventoGateway) {
        this.categoriaEventoGateway = categoriaEventoGateway;
        this.eventoGateway = eventoGateway;
    }

    public void executar(Long idCategoria) {
        categoriaEventoGateway.buscarPorId(idCategoria)
                .orElseThrow(() -> new CategoriaEventoNotFoundException("Categoria não encontrada com ID: " + idCategoria));

        eventoGateway.desvincularCategoriaDosEventos(idCategoria);

        categoriaEventoGateway.deletar(idCategoria);
    }
}

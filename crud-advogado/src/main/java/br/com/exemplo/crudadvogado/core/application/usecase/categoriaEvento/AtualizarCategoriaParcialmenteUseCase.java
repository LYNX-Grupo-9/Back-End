package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento.AtualizarCategoriaCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.CategoriaEventoNotFoundException;
import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;

public class AtualizarCategoriaParcialmenteUseCase {

    private final CategoriaEventoGateway categoriaEventoGateway;

    public AtualizarCategoriaParcialmenteUseCase(CategoriaEventoGateway categoriaEventoGateway) {
        this.categoriaEventoGateway = categoriaEventoGateway;
    }

    public CategoriaEventoResponse executar(Long idCategoria, AtualizarCategoriaCommand command) {
        CategoriaEvento categoria = categoriaEventoGateway.buscarPorId(idCategoria)
                .orElseThrow(() -> new CategoriaEventoNotFoundException("Categoria não encontrada com ID: " + idCategoria));

        if (command.nomeEvento() != null) {
            categoria.setNome(command.nomeEvento());
        }
        if (command.cor() != null) {
            categoria.setCor(command.cor());
        }

        CategoriaEvento categoriaAtualizada = categoriaEventoGateway.atualizar(categoria);

        return new CategoriaEventoResponse(
                categoriaAtualizada.getIdCategoria(),
                categoriaAtualizada.getNome(),
                categoriaAtualizada.getCor()
        );
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento.CriarCategoriaEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CriarCategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;

public class CriarCategoriaEventoUseCase {

    private final CategoriaEventoGateway categoriaEventoGateway;

    public CriarCategoriaEventoUseCase(CategoriaEventoGateway categoriaEventoGateway) {
        this.categoriaEventoGateway = categoriaEventoGateway;
    }

    public CriarCategoriaEventoResponse executar(CriarCategoriaEventoCommand command) {
        CategoriaEvento categoriaEventoParaRegistrar = CategoriaEvento.criarNovo(
                command.nome(),
                command.cor(),
                command.idAdvogado()
        );
        CategoriaEvento categoriaEventoCriado = categoriaEventoGateway.criar(categoriaEventoParaRegistrar);

        return new CriarCategoriaEventoResponse(
                categoriaEventoCriado.getIdCategoria(),
                categoriaEventoCriado.getNome(),
                categoriaEventoCriado.getCor()
        );
    }

}

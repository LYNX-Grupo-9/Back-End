package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaDetalhesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.ContarCategoriasResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContarCategoriasPorNomeUseCase {
    private final CategoriaEventoGateway categoriaEventoGateway;

    public ContarCategoriasPorNomeUseCase(CategoriaEventoGateway categoriaEventoGateway) {
        this.categoriaEventoGateway = categoriaEventoGateway;
    }

    public ContarCategoriasResponse executar(Long idAdvogado) {
        List<Object[]> resultados = categoriaEventoGateway.contarCategoriasAgrupadasPorNome(idAdvogado);

        Map<String, CategoriaDetalhesResponse> mapa = new HashMap<>();

        for (Object[] linha : resultados) {
            String nome = (String) linha[0];
            Long quantidade = (Long) linha[1];
            String cor = (String) linha[2];

            CategoriaDetalhesResponse detalhes = new CategoriaDetalhesResponse(quantidade, cor);
            mapa.put(nome, detalhes);
        }

        return new ContarCategoriasResponse(mapa);
    }
}

package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;

import java.util.List;
import java.util.Optional;

public interface CategoriaEventoGateway {
    CategoriaEvento criar(CategoriaEvento domain);
    Optional<CategoriaEvento> buscarPorNome(String nomeEvento);
    List<CategoriaEvento> buscarPorAdvogadoId(Integer idAdvogado);
    Optional<CategoriaEvento> buscarPorId(Long idCategoria);
    List<Object[]> contarCategoriasAgrupadasPorNome(Long idAdvogado);
}

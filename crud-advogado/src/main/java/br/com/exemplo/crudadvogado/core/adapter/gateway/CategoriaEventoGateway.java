package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaEventoGateway {
    CategoriaEvento criar(CategoriaEvento domain);
    List<CategoriaEvento> buscarTodas();
    Optional<CategoriaEvento> buscarPorNome(String nomeEvento);
    List<CategoriaEvento> buscarPorAdvogadoId(UUID idAdvogado);
    Optional<CategoriaEvento> buscarPorId(Long idCategoria);
    List<Object[]> contarCategoriasAgrupadasPorNome(UUID idAdvogado);
    void deletar(Long idCategoria);
    CategoriaEvento atualizar(CategoriaEvento categoriaEvento);
}

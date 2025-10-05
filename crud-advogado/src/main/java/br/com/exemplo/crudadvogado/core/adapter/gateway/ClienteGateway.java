package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteGateway {
    Cliente criar(Cliente domain);
    Cliente salvar(Cliente domain);
    Long contarPorAdvogadoId(UUID idAdvogado);
    Optional<Cliente> buscarPorId(UUID id);
    List<Cliente> listarTodos();
    List<Cliente> buscarPorAdvogado(UUID advogadoId);
    boolean existePorEmail(String email);
    Long contarPorAdvogado(UUID advogadoId);
    List<Cliente> buscarPorAdvogadoOrdenadoPorNome(UUID advogadoId);
    List<Cliente> buscarPorAdvogadoOrdenadoPorNaturalidade(UUID advogadoId);
    List<Cliente> buscarPorAdvogadoOrdenadoPorDataNascimento(UUID advogadoId);
    List<Cliente> buscarPorTermo(String termo, UUID advogadoId);
    List<Cliente> buscarClientesComQuantidadeProcessos(UUID idAdvogado);
}

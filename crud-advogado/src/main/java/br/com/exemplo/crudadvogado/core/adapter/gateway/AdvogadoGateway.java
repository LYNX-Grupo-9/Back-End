package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Advogado;

import java.util.List;
import java.util.Optional;

public interface AdvogadoGateway {
    Advogado criar(Advogado domain);
    boolean existePorEmail(String email);
    boolean existePorCpf(String cpf);
    boolean existePorOab(String oab);
    Optional<Advogado> buscarPorEmail(String email);
    Optional<Advogado> buscarPorId(Long id);
    List<Advogado> listarTodos();
}

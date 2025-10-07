package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Evento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventoGateway {
    Evento criar(Evento domain);
    Optional<Evento> buscarPorId(Long id);
    List<Evento> buscarPorAdvogado(UUID idAdvogado);
    List<Evento> buscarPorAdvogadoEPeriodo(UUID idAdvogado, LocalDate dataInicio, LocalDate dataFim);
    void deletarPorId(Long id);
    List<Evento> buscarPorAdvogadoEDataAtualOuFutura(UUID idAdvogado);
    void desvincularCategoriaDosEventos(Long idCategoria);
}

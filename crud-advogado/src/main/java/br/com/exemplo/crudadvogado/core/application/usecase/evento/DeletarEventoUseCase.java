package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.exception.EventoNaoEncontradoException;

public class DeletarEventoUseCase {
    private final EventoGateway eventoGateway;

    public DeletarEventoUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public void executar(Long id) {
        eventoGateway.buscarPorId(id)
                .orElseThrow(() -> new EventoNaoEncontradoException("Evento não encontrado"));

        eventoGateway.deletarPorId(id);
    }
}

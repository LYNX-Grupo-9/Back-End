package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.EventoNaoEncontradoException;

public class BuscarEventoPorIdUseCase {

    private final EventoGateway eventoGateway;

    public BuscarEventoPorIdUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public EventoResponse executar(Long id) {
        var evento = eventoGateway.buscarPorId(id)
                .orElseThrow(() -> new EventoNaoEncontradoException("Evento não encontrado"));

        return new EventoResponse(
                evento.getIdEvento(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getLocal(),
                evento.getLinkReuniao(),
                evento.getDataReuniao(),
                evento.getHoraInicio(),
                evento.getHoraFim(),
                evento.getAdvogado(),
                evento.getCliente(),
                evento.getCategoria(),
                evento.getProcesso()
        );
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.domain.Evento;

import java.util.List;
import java.util.UUID;

public class ListarEventosPorClienteUseCase {

    private final EventoGateway eventoGateway;

    public ListarEventosPorClienteUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public List<EventoResponse> executar(UUID idCliente) {
        List<Evento> eventos = eventoGateway.listarPorCliente(idCliente);

        if (eventos.isEmpty()) {
            return List.of();
        }

        return eventos.stream()
                .map(evento -> new EventoResponse(
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
                ))
                .toList();
    }
}

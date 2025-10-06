package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuscarEventosPorAdvogadoUseCase {

    private final EventoGateway eventoGateway;

    public BuscarEventosPorAdvogadoUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public List<EventoResponse> executar(UUID idAdvogado) {
        var eventos = eventoGateway.buscarPorAdvogado(idAdvogado);

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
                .collect(Collectors.toList());
    }
}

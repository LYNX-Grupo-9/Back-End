package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.domain.Evento;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuscarEventosDoMesUseCase {

    private final EventoGateway eventoGateway;

    public BuscarEventosDoMesUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public List<EventoResponse> executar(UUID idAdvogado) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date inicioMes = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date fimMes = calendar.getTime();

        List<Evento> eventos = eventoGateway.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                idAdvogado, inicioMes, fimMes
        );

        return eventos.stream()
                .map(EventoResponse::from)
                .collect(Collectors.toList());
    }
}

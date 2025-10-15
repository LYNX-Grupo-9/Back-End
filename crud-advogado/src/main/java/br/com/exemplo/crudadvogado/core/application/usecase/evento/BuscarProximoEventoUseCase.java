package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.domain.Evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuscarProximoEventoUseCase {

    private final EventoGateway eventoGateway;

    public BuscarProximoEventoUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public Optional<EventoResponse> executar(UUID idAdvogado) {
        LocalDateTime agora = LocalDateTime.now();

        var eventosFuturos = eventoGateway.buscarPorAdvogadoEDataAtualOuFutura(idAdvogado);

        var eventosValidos = eventosFuturos.stream()
                .filter(evento -> {
                    LocalDate dataReuniaoLocal = evento.getDataReuniao().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

                    LocalTime horaEvento = evento.getHoraInicio() != null ? evento.getHoraInicio() : LocalTime.MIN;
                    LocalDateTime dataHoraEvento = LocalDateTime.of(dataReuniaoLocal, horaEvento);
                    return !dataHoraEvento.isBefore(agora);
                })
                .sorted(Comparator
                        .comparing((Evento e) -> e.getDataReuniao().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate())
                        .thenComparing(e -> e.getHoraInicio() != null ? e.getHoraInicio() : LocalTime.MIN))
                .collect(Collectors.toList());

        return eventosValidos.isEmpty() ?
                Optional.empty() :
                Optional.of(new EventoResponse(
                        eventosValidos.get(0).getIdEvento(),
                        eventosValidos.get(0).getNome(),
                        eventosValidos.get(0).getDescricao(),
                        eventosValidos.get(0).getLocal(),
                        eventosValidos.get(0).getLinkReuniao(),
                        eventosValidos.get(0).getDataReuniao(),
                        eventosValidos.get(0).getHoraInicio(),
                        eventosValidos.get(0).getHoraFim(),
                        eventosValidos.get(0).getAdvogado(),
                        eventosValidos.get(0).getCliente(),
                        eventosValidos.get(0).getCategoria(),
                        eventosValidos.get(0).getProcesso()
                ));
    }
}

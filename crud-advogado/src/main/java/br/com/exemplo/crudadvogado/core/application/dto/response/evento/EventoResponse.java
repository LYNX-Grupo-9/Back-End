package br.com.exemplo.crudadvogado.core.application.dto.response.evento;

import br.com.exemplo.crudadvogado.core.domain.Evento;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public record EventoResponse(
        Long idEvento,
        String nome,
        String descricao,
        String local,
        String linkReuniao,
        Date dataReuniao,
        LocalTime horaInicio,
        LocalTime horaFim,
        UUID advogado,
        UUID cliente,
        Long categoria,
        UUID processo
) {
    public static EventoResponse from(Evento evento) {
        return new EventoResponse(
                evento.getIdEvento(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getLocal(),
                evento.getLinkReuniao(),
                evento.getDataReuniao(),
                evento.getHoraInicio(),
                evento.getHoraFim(),
                evento.getAdvogado() != null ? evento.getAdvogado() : null,
                evento.getCliente() != null ? evento.getCliente() : null,
                evento.getCategoria() != null ? evento.getCategoria() : null,
                evento.getProcesso() != null ? evento.getProcesso() : null
        );
    }
}

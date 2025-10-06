package br.com.exemplo.crudadvogado.core.application.dto.response.evento;

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
}

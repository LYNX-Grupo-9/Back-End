package br.com.exemplo.crudadvogado.core.application.dto.command.evento;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public record CriarEventoCommand(
        String nome,
        String descricao,
        String local,
        String linkReuniao,
        Date dataReuniao,
        LocalTime horaInicio,
        LocalTime horaFim,
        UUID idAdvogado,
        UUID idCliente,
        Long idCategoria,
        UUID idProcesso
) {
}

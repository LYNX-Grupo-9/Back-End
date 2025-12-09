package br.com.exemplo.crudadvogado.core.application.dto.response.cliente;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ClienteProcessoEventoResponse(
        UUID idCliente,
        String nome,
        String documento,
        String tipoDocumento,
        Email email,
        String telefone,
        String endereco,
        Genero genero,
        Date dataNascimento,
        EstadoCivil estadoCivil,
        String profissao,
        String passaporte,
        String cnh,
        String naturalidade,
        List<ProcessoResponse> processos,
        List<EventoResponse> eventos
) {
    public record ProcessoResponse(
            String numeroProcesso
    ) {}

    public record EventoResponse(
            Long idEvento,
            Date dataEvento,
            LocalTime horaInicio,
            LocalTime horaFim,
            String titulo,
            Long tipo
    ) {
        public String nome() {
            return "";
        }
    }
}

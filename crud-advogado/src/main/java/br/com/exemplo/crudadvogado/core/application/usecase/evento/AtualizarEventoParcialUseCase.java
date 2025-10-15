package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.*;
import br.com.exemplo.crudadvogado.core.application.dto.command.evento.AtualizarEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.EventoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Evento;

public class AtualizarEventoParcialUseCase {
    private final EventoGateway eventoGateway;

    public AtualizarEventoParcialUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public EventoResponse executar(Long id, AtualizarEventoCommand request) {
        Evento evento = eventoGateway.buscarPorId(id)
                .orElseThrow(() -> new EventoNaoEncontradoException("Evento não encontrado"));

        atualizarCampos(evento, request);

        Evento eventoAtualizado = eventoGateway.criar(evento);
        return EventoResponse.from(eventoAtualizado);
    }

    private void atualizarCampos(Evento evento, AtualizarEventoCommand request) {
        if (request.nome() != null) {
            evento.setNome(request.nome());
        }
        if (request.descricao() != null) {
            evento.setDescricao(request.descricao());
        }
        if (request.dataReuniao() != null) {
            evento.setDataReuniao(request.dataReuniao());
        }
        if (request.horaInicio() != null) {
            evento.setHoraInicio(request.horaInicio());
        }
        if (request.horaFim() != null) {
            evento.setHoraFim(request.horaFim());
        }
        if (request.local() != null) {
            evento.setLocal(request.local());
        }
        if (request.linkReuniao() != null) {
            evento.setLinkReuniao(request.linkReuniao());
        }
        if (request.idCliente() != null) {
            evento.setCliente(request.idCliente());
        }
        if (request.idCategoria() != null) {
            evento.setCategoria(request.idCategoria());
        }
        if (request.idProcesso() != null) {
            evento.setProcesso(request.idProcesso());
        }
    }
}

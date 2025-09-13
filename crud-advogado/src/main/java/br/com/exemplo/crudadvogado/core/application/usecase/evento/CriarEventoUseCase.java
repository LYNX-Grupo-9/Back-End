package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento.CriarCategoriaEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.evento.CriarEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.CriarEventoResponse;
import br.com.exemplo.crudadvogado.core.domain.Evento;

public class CriarEventoUseCase {

    private final EventoGateway eventoGateway;

    public CriarEventoUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public CriarEventoResponse executar(CriarEventoCommand command){
        Evento eventoParaRegistrar = Evento.criarNovo(
                command.nome(),
                command.descricao(),
                command.local(),
                command.linkReuniao(),
                command.dataReuniao(),
                command.horaInicio(),
                command.horaFim(),
                command.idAdvogado(),
                command.idCliente(),
                command.idCategoria()
        );
        Evento eventoCriado = eventoGateway.criar(eventoParaRegistrar);

        return new CriarEventoResponse(
                eventoCriado.getIdEvento(),
                eventoCriado.getNome(),
                eventoCriado.getDescricao(),
                eventoCriado.getLocal(),
                eventoCriado.getLinkReuniao(),
                eventoCriado.getDataReuniao(),
                eventoCriado.getHoraInicio(),
                eventoCriado.getHoraFim(),
                eventoCriado.getAdvogado(),
                eventoCriado.getCliente(),
                eventoCriado.getCategoria()
        );
    }

}

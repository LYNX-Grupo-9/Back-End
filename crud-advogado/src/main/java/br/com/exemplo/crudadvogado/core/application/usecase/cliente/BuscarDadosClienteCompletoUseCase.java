package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteProcessoEventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ClienteNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import br.com.exemplo.crudadvogado.core.domain.Processo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuscarDadosClienteCompletoUseCase {

    private final ClienteGateway clienteGateway;
    private final ProcessoGateway processoGateway;
    private final EventoGateway eventoGateway;

    public BuscarDadosClienteCompletoUseCase(
            ClienteGateway clienteGateway,
            ProcessoGateway processoGateway,
            EventoGateway eventoGateway) {
        this.clienteGateway = clienteGateway;
        this.processoGateway = processoGateway;
        this.eventoGateway = eventoGateway;
    }

    public ClienteProcessoEventoResponse executar(UUID idCliente) {
        Cliente cliente = clienteGateway.buscarPorId(idCliente)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        List<Processo> processosCompletos = cliente.getProcessos().stream()
                .map(idProcesso -> {
                    System.out.println("Buscando processo ID: " + idProcesso);
                    return processoGateway.buscarPorId(idProcesso).orElse(null);
                })
                .filter(e -> e != null)
                .collect(Collectors.toList());

        List<Evento> eventosCompletos = cliente.getEventos().stream()
                .map(eventoId -> {
                    System.out.println("Buscando evento ID: " + eventoId);
                    return eventoGateway.buscarPorId(eventoId).orElse(null);
                })
                .filter(e -> e != null)
                .collect(Collectors.toList());

        List<ClienteProcessoEventoResponse.ProcessoResponse> processos = processosCompletos.stream()
                .map(this::mapearProcesso)
                .collect(Collectors.toList());

        List<ClienteProcessoEventoResponse.EventoResponse> eventos = eventosCompletos.stream()
                .map(this::mapearEvento)
                .collect(Collectors.toList());

        return new ClienteProcessoEventoResponse(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getDocumento(),
                cliente.getTipoDocumento(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getGenero(),
                cliente.getDataNascimento(),
                cliente.getEstadoCivil(),
                cliente.getProfissao(),
                cliente.getPassaporte(),
                cliente.getCnh(),
                cliente.getNaturalidade(),
                processos,
                eventos
        );
    }

    private ClienteProcessoEventoResponse.ProcessoResponse mapearProcesso(Processo processo) {
        return new ClienteProcessoEventoResponse.ProcessoResponse(
                processo.getNumeroProcesso()
        );
    }

    private ClienteProcessoEventoResponse.EventoResponse mapearEvento(Evento evento) {
        return new ClienteProcessoEventoResponse.EventoResponse(
                evento.getIdEvento(),
                evento.getDataReuniao(),
                evento.getHoraInicio(),
                evento.getHoraFim(),
                evento.getNome(),
                evento.getCategoria()
        );
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ClienteNaoEncontradoException;

import java.util.UUID;

public class BuscarClienteComQuantidadeProcessosUseCase {

    private final ClienteGateway clienteGateway;

    public BuscarClienteComQuantidadeProcessosUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ClienteResponse executar(UUID idCliente) {
        var cliente = clienteGateway.buscarPorId(idCliente)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + idCliente));

        return ClienteResponse.fromDomain(cliente);
    }
}

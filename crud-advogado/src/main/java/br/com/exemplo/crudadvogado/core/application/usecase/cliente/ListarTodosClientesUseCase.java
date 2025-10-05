package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;

import java.util.List;

public class ListarTodosClientesUseCase {
    private final ClienteGateway clienteGateway;
    private final ProcessoGateway processoGateway;

    public ListarTodosClientesUseCase(ClienteGateway clienteGateway, ProcessoGateway processoGateway) {
        this.clienteGateway = clienteGateway;
        this.processoGateway = processoGateway;
    }

    public List<ClienteResponse> executar() {
        return clienteGateway.listarTodos()
                .stream()
                .map(cliente -> {
                    Long qtdProcessos = processoGateway.contarProcessosPorCliente(cliente.getIdCliente());
                    return ClienteResponse.fromDomain(cliente);
                })
                .toList();
    }
}

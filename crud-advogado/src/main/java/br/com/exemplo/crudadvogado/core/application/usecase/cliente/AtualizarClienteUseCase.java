package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;

public class AtualizarClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final AdvogadoGateway advogadoGateway;

    public AtualizarClienteUseCase(ClienteGateway clienteGateway, AdvogadoGateway advogadoGateway) {
        this.clienteGateway = clienteGateway;
        this.advogadoGateway = advogadoGateway;
    }

}

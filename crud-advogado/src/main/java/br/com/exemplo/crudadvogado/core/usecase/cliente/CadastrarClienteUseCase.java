package br.com.exemplo.crudadvogado.core.usecase.cliente;

import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.gateway.ClienteGateway;

public class CadastrarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public CadastrarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public Cliente executar(Cliente cliente) {
        // Aqui poderia validar CPF, e-mail único, etc.
        return clienteGateway.salvar(cliente);
    }

}

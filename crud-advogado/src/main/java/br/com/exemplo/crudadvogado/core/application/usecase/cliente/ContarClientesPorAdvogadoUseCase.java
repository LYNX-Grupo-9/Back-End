package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;

import java.util.UUID;

public class ContarClientesPorAdvogadoUseCase {

    private final ClienteGateway clienteGateway;
    private final AdvogadoGateway advogadoGateway;

    public ContarClientesPorAdvogadoUseCase(ClienteGateway clienteGateway,
                                            AdvogadoGateway advogadoGateway) {
        this.clienteGateway = clienteGateway;
        this.advogadoGateway = advogadoGateway;
    }

    public Long executar(UUID idAdvogado) {
        advogadoGateway.buscarPorId(idAdvogado)
                .orElseThrow(() -> new AdvogadoNaoEncontradoException("Advogado não encontrado."));

        return clienteGateway.contarPorAdvogadoId(idAdvogado);
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;

import java.util.List;
import java.util.UUID;

public class BuscarClientesPorTextoUseCase {

    private final ClienteGateway clienteGateway;
    private final AdvogadoGateway advogadoGateway;

    public BuscarClientesPorTextoUseCase(ClienteGateway clienteGateway, AdvogadoGateway advogadoGateway) {
        this.clienteGateway = clienteGateway;
        this.advogadoGateway = advogadoGateway;
    }

    public List<ClienteResponse> executar(String termo, UUID idAdvogado) {
        validarAdvogado(idAdvogado);

        return clienteGateway.buscarPorTermo(termo, idAdvogado)
                .stream()
                .map(ClienteResponse::fromDomain)
                .toList();
    }

    private void validarAdvogado(UUID idAdvogado) {
        if (!advogadoGateway.existePorId(idAdvogado)) {
            throw new AdvogadoNaoEncontradoException("Advogado não encontrado com ID: " + idAdvogado);
        }
    }
}

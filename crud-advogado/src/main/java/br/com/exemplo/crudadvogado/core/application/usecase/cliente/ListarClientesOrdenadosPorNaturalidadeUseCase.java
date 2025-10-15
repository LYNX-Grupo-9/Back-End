package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ListarClientesOrdenadosPorNaturalidadeUseCase {

    private final ClienteGateway clienteGateway;
    private final AdvogadoGateway advogadoGateway;
    private final ProcessoGateway processoGateway;

    public ListarClientesOrdenadosPorNaturalidadeUseCase(ClienteGateway clienteGateway, AdvogadoGateway advogadoGateway, ProcessoGateway processoGateway) {
        this.clienteGateway = clienteGateway;
        this.advogadoGateway = advogadoGateway;
        this.processoGateway = processoGateway;
    }

    public List<ClienteResponse> executar(UUID idAdvogado) {
        validarAdvogado(idAdvogado);

        List<ClienteResponse> clientes = clienteGateway.buscarPorAdvogado(idAdvogado)
                .stream()
                .map(cliente -> {
                    Long qtdProcessos = processoGateway.contarProcessosPorCliente(cliente.getIdCliente());
                    return ClienteResponse.fromDomain(cliente);
                })
                .sorted(Comparator.comparing(ClienteResponse::qtdProcessos).reversed())
                .toList();

        return clientes;
    }

    private void validarAdvogado(UUID idAdvogado) {
        if (!advogadoGateway.existePorId(idAdvogado)) {
            throw new AdvogadoNaoEncontradoException("Advogado não encontrado com ID: " + idAdvogado);
        }
    }
}

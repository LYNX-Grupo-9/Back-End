package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;

import java.util.List;
import java.util.UUID;

public class ListarClientesPorAdvogadoUseCase {

    private final ClienteGateway clienteGateway;
    private final AdvogadoGateway advogadoGateway;
    private final ProcessoGateway processoGateway;

    public ListarClientesPorAdvogadoUseCase(ClienteGateway clienteGateway, AdvogadoGateway advogadoGateway, ProcessoGateway processoGateway) {
        this.clienteGateway = clienteGateway;
        this.advogadoGateway = advogadoGateway;
        this.processoGateway = processoGateway;
    }

    public List<ClienteResponse> executar(UUID advogadoId) {
        validarAdvogado(advogadoId);

        return clienteGateway.buscarPorAdvogado(advogadoId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    private void validarAdvogado(UUID advogadoId) {
        if (!advogadoGateway.existePorId(advogadoId)) {
            throw new AdvogadoNaoEncontradoException(advogadoId.toString());
        }
    }

    private ClienteResponse mapToResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getDocumento(),
                cliente.getTipoDocumento(),
                cliente.getEmail().getEnderacoEmail(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getGenero().getValor(),
                cliente.getDataNascimento(),
                cliente.getEstadoCivil().getValor(),
                cliente.getProfissao(),
                cliente.getPassaporte(),
                cliente.getCnh(),
                cliente.getNaturalidade(),
                cliente.getIdAdvogado(),
                cliente.getQtdProcessos()
        );
    }

}

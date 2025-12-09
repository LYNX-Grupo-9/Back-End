package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ListarClientesPorAdvogadoUseCase {

    private final ClienteGateway clienteGateway;
    private final AdvogadoGateway advogadoGateway;

    public ListarClientesPorAdvogadoUseCase(ClienteGateway clienteGateway, AdvogadoGateway advogadoGateway) {
        this.clienteGateway = clienteGateway;
        this.advogadoGateway = advogadoGateway;
    }

    @Cacheable(value = "clientesPorAdvogado", key = "#advogadoId")
    public List<ClienteResponse> executar(UUID advogadoId) {
        System.out.println("🚀 CONSULTANDO BANCO - Clientes do advogado: " + advogadoId);

        validarAdvogado(advogadoId);

        List<Cliente> clientes = clienteGateway.buscarPorAdvogado(advogadoId);

        return clientes.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
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

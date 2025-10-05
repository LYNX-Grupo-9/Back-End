package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.AtualizarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ClienteNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;

import java.util.UUID;

public class AtualizarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public AtualizarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ClienteResponse executar(UUID idCliente, AtualizarClienteCommand command) {
        var cliente = clienteGateway.buscarPorId(idCliente)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado."));

        atualizarCamposCliente(cliente, command);

        var clienteAtualizado = clienteGateway.salvar(cliente);

        return ClienteResponse.fromDomain(clienteAtualizado);
    }

    private void atualizarCamposCliente(Cliente cliente, AtualizarClienteCommand command) {
        if (command.nome() != null) cliente.setNome(command.nome());
        if (command.documento() != null) cliente.setDocumento(command.documento());
        if (command.email() != null) cliente.setEmail(command.email());
        if (command.telefone() != null) cliente.setTelefone(command.telefone());
        if (command.endereco() != null) cliente.setEndereco(command.endereco());
        if (command.estadoCivil() != null) cliente.setEstadoCivil(command.estadoCivil());
        if (command.genero() != null) cliente.setGenero(command.genero());
        if (command.profissao() != null) cliente.setProfissao(command.profissao());
        if (command.passaporte() != null) cliente.setPassaporte(command.passaporte());
        if (command.cnh() != null) cliente.setCnh(command.cnh());
        if (command.naturalidade() != null) cliente.setNaturalidade(command.naturalidade());
        if (command.dataNascimento() != null) cliente.setDataNascimento(command.dataNascimento());
    }
}

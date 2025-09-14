package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.CriarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.CriarClienteResponse;
import br.com.exemplo.crudadvogado.core.domain.Cliente;

public class CriarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public CriarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public CriarClienteResponse executar(CriarClienteCommand command){
        Cliente clienteParaRegistrar = Cliente.criarNovo(
                command.nome(),
                command.documento(),
                command.tipoDocumento(),
                command.email(),
                command.telefone(),
                command.endereco(),
                command.genero(),
                command.dataNascimento(),
                command.estadoCivil(),
                command.profissao(),
                command.passaporte(),
                command.cnh(),
                command.naturalidade(),
                command.idAdvogado()
                );

        Cliente clienteCriado = clienteGateway.criar(clienteParaRegistrar);

        return new CriarClienteResponse(
                clienteCriado.getIdCliente(),
                clienteCriado.getNome(),
                clienteCriado.getDocumento(),
                clienteCriado.getTipoDocumento(),
                clienteCriado.getEmail().getEnderacoEmail(),
                clienteCriado.getTelefone(),
                clienteCriado.getEndereco(),
                clienteCriado.getGenero().getValor(),
                clienteCriado.getDataNascimento(),
                clienteCriado.getEstadoCivil().getValor(),
                clienteCriado.getProfissao(),
                clienteCriado.getPassaporte(),
                clienteCriado.getCnh(),
                clienteCriado.getNaturalidade(),
                clienteCriado.getQtdProcessos(),
                clienteCriado.getIdAdvogado()
        );
    }
}

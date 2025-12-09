package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.AtualizarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ClienteNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarClienteUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private AtualizarClienteUseCase atualizarClienteUseCase;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setIdCliente(UUID.randomUUID());
        cliente.setNome("Nome Antigo");
        cliente.setDocumento("123456789");
        cliente.setEmail(Email.criar("antigo@email.com"));
        cliente.setTelefone("11999999999");
        cliente.setProfissao("Analista");
    }

    // ================================
    // ✅ SUCESSO
    // ================================
    @Test
    void deveAtualizarClienteComSucesso() {
        UUID idCliente = cliente.getIdCliente();

        AtualizarClienteCommand command = new AtualizarClienteCommand(
                "Nome Novo",              // nome
                null,                     // documento
                "novo@email.com",         // email
                null,                     // telefone
                null,                     // endereco
                null,                     // genero
                new Date(),               // dataNascimento
                null,                     // estadoCivil
                "Engenheiro",             // profissao
                null,                     // passaporte
                null,                     // cnh
                null                      // naturalidade
        );

        when(clienteGateway.buscarPorId(idCliente))
                .thenReturn(Optional.of(cliente));

        when(clienteGateway.salvar(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ClienteResponse response =
                atualizarClienteUseCase.executar(idCliente, command);

        assertNotNull(response);
        assertEquals("Nome Novo", response.nome());
        assertEquals("novo@email.com", response.email());
        assertEquals("Engenheiro", response.profissao());

        ArgumentCaptor<Cliente> captor =
                ArgumentCaptor.forClass(Cliente.class);

        verify(clienteGateway).salvar(captor.capture());

        Cliente clienteSalvo = captor.getValue();
        assertEquals("Nome Novo", clienteSalvo.getNome());
        assertEquals("novo@email.com", clienteSalvo.getEmail());
        assertEquals("Engenheiro", clienteSalvo.getProfissao());
    }

    // ================================
    // ❌ CLIENTE NÃO EXISTE
    // ================================
    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {
        UUID idCliente = UUID.randomUUID();

        when(clienteGateway.buscarPorId(idCliente))
                .thenReturn(Optional.empty());

        AtualizarClienteCommand command = new AtualizarClienteCommand(
                "Nome",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        assertThrows(
                ClienteNaoEncontradoException.class,
                () -> atualizarClienteUseCase.executar(idCliente, command)
        );

        verify(clienteGateway).buscarPorId(idCliente);
        verify(clienteGateway, never()).salvar(any());
    }
}

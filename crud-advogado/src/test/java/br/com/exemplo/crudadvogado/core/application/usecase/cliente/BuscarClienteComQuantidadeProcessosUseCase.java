package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ClienteNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarClienteComQuantidadeProcessosUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private BuscarClienteComQuantidadeProcessosUseCase buscarClienteUseCase;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setIdCliente(UUID.randomUUID());
        cliente.setNome("João Silva");
        cliente.setDocumento("123456789");
        cliente.setEmail(Email.criar("joao@email.com"));
        cliente.setTelefone("11999999999");
    }

    // ========================================
    // ✅ SUCESSO — CLIENTE EXISTE
    // ========================================
    @Test
    void deveBuscarClienteComSucesso() {
        // ARRANGE
        UUID idCliente = cliente.getIdCliente();

        when(clienteGateway.buscarPorId(idCliente))
                .thenReturn(Optional.of(cliente));

        // ACT
        ClienteResponse response =
                buscarClienteUseCase.executar(idCliente);

        // ASSERT
        assertNotNull(response);
        assertEquals(cliente.getIdCliente(), response.idCliente());
        assertEquals("João Silva", response.nome());
        assertEquals("123456789", response.documento());
        assertEquals("joao@email.com", response.email());

        verify(clienteGateway, times(1))
                .buscarPorId(idCliente);
    }

    // ========================================
    // ❌ CLIENTE NÃO EXISTE
    // ========================================
    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {
        // ARRANGE
        UUID idCliente = UUID.randomUUID();

        when(clienteGateway.buscarPorId(idCliente))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        ClienteNaoEncontradoException exception =
                assertThrows(
                        ClienteNaoEncontradoException.class,
                        () -> buscarClienteUseCase.executar(idCliente)
                );

        assertEquals(
                "Cliente não encontrado com ID: " + idCliente,
                exception.getMessage()
        );

        verify(clienteGateway, times(1))
                .buscarPorId(idCliente);
    }
}

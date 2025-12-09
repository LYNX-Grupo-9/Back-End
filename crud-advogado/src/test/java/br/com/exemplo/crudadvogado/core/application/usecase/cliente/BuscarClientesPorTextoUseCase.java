package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarClientesPorTextoUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private AdvogadoGateway advogadoGateway;

    @InjectMocks
    private BuscarClientesPorTextoUseCase buscarClientesPorTextoUseCase;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setIdCliente(UUID.randomUUID());
        cliente.setNome("Maria Silva");
        cliente.setDocumento("123456789");
        cliente.setEmail(Email.criar("maria@email.com"));
        cliente.setTelefone("11988887777");
    }

    // ========================================
    // ✅ SUCESSO — ADVOGADO EXISTE
    // ========================================
    @Test
    void deveBuscarClientesPorTextoComSucesso() {
        // ARRANGE
        String termo = "Maria";
        UUID idAdvogado = UUID.randomUUID();

        when(advogadoGateway.existePorId(idAdvogado))
                .thenReturn(true);

        when(clienteGateway.buscarPorTermo(termo, idAdvogado))
                .thenReturn(List.of(cliente));

        // ACT
        List<ClienteResponse> response =
                buscarClientesPorTextoUseCase.executar(termo, idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(cliente.getIdCliente(), response.get(0).idCliente());
        assertEquals("Maria Silva", response.get(0).nome());
        assertEquals("maria@email.com", response.get(0).email());

        verify(advogadoGateway, times(1))
                .existePorId(idAdvogado);

        verify(clienteGateway, times(1))
                .buscarPorTermo(termo, idAdvogado);
    }

    // ========================================
    // ❌ ERRO — ADVOGADO NÃO EXISTE
    // ========================================
    @Test
    void deveLancarExcecaoQuandoAdvogadoNaoExistir() {
        // ARRANGE
        String termo = "Maria";
        UUID idAdvogado = UUID.randomUUID();

        when(advogadoGateway.existePorId(idAdvogado))
                .thenReturn(false);

        // ACT + ASSERT
        AdvogadoNaoEncontradoException exception =
                assertThrows(
                        AdvogadoNaoEncontradoException.class,
                        () -> buscarClientesPorTextoUseCase.executar(termo, idAdvogado)
                );

        assertEquals(
                "Advogado não encontrado com ID: " + idAdvogado,
                exception.getMessage()
        );

        verify(advogadoGateway, times(1))
                .existePorId(idAdvogado);

        verify(clienteGateway, never())
                .buscarPorTermo(any(), any());
    }
}

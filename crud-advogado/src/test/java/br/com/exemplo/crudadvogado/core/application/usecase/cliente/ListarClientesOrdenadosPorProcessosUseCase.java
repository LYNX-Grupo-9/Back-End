package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
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
class ListarClientesOrdenadosPorProcessosUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private AdvogadoGateway advogadoGateway;

    @Mock
    private ProcessoGateway processoGateway;

    @InjectMocks
    private ListarClientesOrdenadosPorProcessosUseCase useCase;

    private UUID idAdvogado;
    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setup() {
        idAdvogado = UUID.randomUUID();

        cliente1 = new Cliente();
        cliente1.setIdCliente(UUID.randomUUID());
        cliente1.setNome("Carlos");

        cliente2 = new Cliente();
        cliente2.setIdCliente(UUID.randomUUID());
        cliente2.setNome("Mariana");
    }

    // ==================================================
    // ✅ SUCESSO — ORDENAÇÃO POR QUANTIDADE DE PROCESSOS
    // ==================================================
    @Test
    void deveListarClientesOrdenadosPorQuantidadeDeProcessos() {
        // ARRANGE
        when(advogadoGateway.existePorId(idAdvogado))
                .thenReturn(true);

        when(clienteGateway.buscarPorAdvogado(idAdvogado))
                .thenReturn(List.of(cliente1, cliente2));

        when(processoGateway.contarProcessosPorCliente(cliente1.getIdCliente()))
                .thenReturn(3L);

        when(processoGateway.contarProcessosPorCliente(cliente2.getIdCliente()))
                .thenReturn(8L);

        // ACT
        List<ClienteResponse> response =
                useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertEquals(2, response.size());

        // cliente2 deve vir primeiro (8 processos)
        assertEquals(cliente2.getIdCliente(), response.get(0).idCliente());
        assertEquals(cliente1.getIdCliente(), response.get(1).idCliente());

        verify(advogadoGateway, times(1))
                .existePorId(idAdvogado);

        verify(clienteGateway, times(1))
                .buscarPorAdvogado(idAdvogado);

        verify(processoGateway, times(1))
                .contarProcessosPorCliente(cliente1.getIdCliente());

        verify(processoGateway, times(1))
                .contarProcessosPorCliente(cliente2.getIdCliente());
    }

    // ==================================================
    // ❌ ERRO — ADVOGADO NÃO ENCONTRADO
    // ==================================================
    @Test
    void deveLancarExcecaoQuandoAdvogadoNaoExistir() {
        // ARRANGE
        when(advogadoGateway.existePorId(idAdvogado))
                .thenReturn(false);

        // ACT + ASSERT
        AdvogadoNaoEncontradoException exception =
                assertThrows(
                        AdvogadoNaoEncontradoException.class,
                        () -> useCase.executar(idAdvogado)
                );

        assertEquals(
                "Advogado não encontrado com ID: " + idAdvogado,
                exception.getMessage()
        );

        verify(advogadoGateway, times(1))
                .existePorId(idAdvogado);

        verifyNoInteractions(clienteGateway);
        verifyNoInteractions(processoGateway);
    }
}

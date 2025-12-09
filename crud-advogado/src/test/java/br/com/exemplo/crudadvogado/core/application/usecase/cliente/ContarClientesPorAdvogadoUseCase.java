package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Advogado;
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
class ContarClientesPorAdvogadoUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private AdvogadoGateway advogadoGateway;

    @InjectMocks
    private ContarClientesPorAdvogadoUseCase contarClientesPorAdvogadoUseCase;

    // ==================================================
    // ✅ SUCESSO — CONTAGEM REALIZADA
    // ==================================================
    @Test
    void deveContarClientesPorAdvogadoComSucesso() {
        // ARRANGE
        UUID idAdvogado = UUID.randomUUID();
        Long totalClientes = 5L;

        Advogado advogado = new Advogado();
        advogado.setIdAdvogado(idAdvogado);

        when(advogadoGateway.buscarPorId(idAdvogado))
                .thenReturn(Optional.of(advogado));

        when(clienteGateway.contarPorAdvogadoId(idAdvogado))
                .thenReturn(totalClientes);

        // ACT
        Long resultado = contarClientesPorAdvogadoUseCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(resultado);
        assertEquals(5L, resultado);

        verify(advogadoGateway, times(1))
                .buscarPorId(idAdvogado);

        verify(clienteGateway, times(1))
                .contarPorAdvogadoId(idAdvogado);
    }

    // ==================================================
    // ❌ ERRO — ADVOGADO NÃO ENCONTRADO
    // ==================================================
    @Test
    void deveLancarExcecaoQuandoAdvogadoNaoExistir() {
        // ARRANGE
        UUID idAdvogado = UUID.randomUUID();

        when(advogadoGateway.buscarPorId(idAdvogado))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        AdvogadoNaoEncontradoException exception =
                assertThrows(
                        AdvogadoNaoEncontradoException.class,
                        () -> contarClientesPorAdvogadoUseCase.executar(idAdvogado)
                );

        assertEquals("Advogado não encontrado.", exception.getMessage());

        verify(advogadoGateway, times(1))
                .buscarPorId(idAdvogado);

        verifyNoInteractions(clienteGateway);
    }
}

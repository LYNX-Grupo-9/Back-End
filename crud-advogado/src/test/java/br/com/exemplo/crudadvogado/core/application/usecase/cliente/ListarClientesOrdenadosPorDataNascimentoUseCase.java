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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarClientesOrdenadosPorDataNascimentoUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private AdvogadoGateway advogadoGateway;

    @Mock
    private ProcessoGateway processoGateway;

    @InjectMocks
    private ListarClientesOrdenadosPorDataNascimentoUseCase useCase;

    private UUID idAdvogado;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setup() {
        idAdvogado = UUID.randomUUID();

        cliente1 = new Cliente();
        cliente1.setIdCliente(UUID.randomUUID());
        cliente1.setNome("Cliente A");
        cliente1.setDataNascimento(LocalDate.of(1990, 1, 1));

        cliente2 = new Cliente();
        cliente2.setIdCliente(UUID.randomUUID());
        cliente2.setNome("Cliente B");
        cliente2.setDataNascimento(LocalDate.of(2000, 1, 1));
    }

    // ============================================
    // ✅ Deve listar clientes ordenados por qtdProcessos (lógica atual)
    // ============================================
    @Test
    void deveListarClientesOrdenadosPorQtdProcessos() {
        // ARRANGE
        when(advogadoGateway.existePorId(idAdvogado)).thenReturn(true);
        when(clienteGateway.buscarPorAdvogado(idAdvogado))
                .thenReturn(Arrays.asList(cliente1, cliente2));

        // qtdProcessos
        when(processoGateway.contarProcessosPorCliente(cliente1.getIdCliente()))
                .thenReturn(5L); // maior
        when(processoGateway.contarProcessosPorCliente(cliente2.getIdCliente()))
                .thenReturn(1L); // menor

        // ACT
        List<ClienteResponse> resultado = useCase.executar(idAdvogado);

        // ASSERT
        assertEquals(2, resultado.size());

        // Deve vir ordenado: cliente1 primeiro (5 processos)
        assertEquals("Cliente A", resultado.get(0).nome());
        assertEquals("Cliente B", resultado.get(1).nome());

        verify(advogadoGateway, times(1)).existePorId(idAdvogado);
        verify(clienteGateway, times(1)).buscarPorAdvogado(idAdvogado);
        verify(processoGateway, times(1)).contarProcessosPorCliente(cliente1.getIdCliente());
        verify(processoGateway, times(1)).contarProcessosPorCliente(cliente2.getIdCliente());
    }

    // ============================================
    // ❌ Deve lançar erro se advogado não existir
    // ============================================
    @Test
    void deveLancarErroQuandoAdvogadoNaoExiste() {
        when(advogadoGateway.existePorId(idAdvogado)).thenReturn(false);

        assertThrows(AdvogadoNaoEncontradoException.class,
                () -> useCase.executar(idAdvogado));

        verify(clienteGateway, never()).buscarPorAdvogado(any());
    }
}

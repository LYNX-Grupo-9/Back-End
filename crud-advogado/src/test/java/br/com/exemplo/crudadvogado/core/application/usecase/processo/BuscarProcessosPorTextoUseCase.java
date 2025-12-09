package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarProcessosPorTextoUseCaseTest {

    private ProcessoGateway processoGateway;
    private BuscarProcessosPorTextoUseCase buscarProcessosPorTextoUseCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        buscarProcessosPorTextoUseCase = new BuscarProcessosPorTextoUseCase(processoGateway);
    }

    @Test
    void deveRetornarListaDeProcessosQuandoExistirem() {
        // dado
        String termo = "123";
        UUID idAdvogado = UUID.randomUUID();
        Processo processo1 = new Processo(); // configure campos se necessário
        Processo processo2 = new Processo();

        when(processoGateway.buscarPorNumeroTituloOuCliente(termo, idAdvogado))
                .thenReturn(List.of(processo1, processo2));

        // quando
        List<ProcessoResponse> resultados = buscarProcessosPorTextoUseCase.executar(termo, idAdvogado);

        // então
        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        assertEquals(processo1, resultados.get(0).toDomain()); // assumindo método toDomain()
        assertEquals(processo2, resultados.get(1).toDomain());
        verify(processoGateway, times(1)).buscarPorNumeroTituloOuCliente(termo, idAdvogado);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremProcessos() {
        // dado
        String termo = "nãoExiste";
        UUID idAdvogado = UUID.randomUUID();

        when(processoGateway.buscarPorNumeroTituloOuCliente(termo, idAdvogado))
                .thenReturn(List.of());

        // quando
        List<ProcessoResponse> resultados = buscarProcessosPorTextoUseCase.executar(termo, idAdvogado);

        // então
        assertNotNull(resultados);
        assertTrue(resultados.isEmpty());
        verify(processoGateway, times(1)).buscarPorNumeroTituloOuCliente(termo, idAdvogado);
    }
}

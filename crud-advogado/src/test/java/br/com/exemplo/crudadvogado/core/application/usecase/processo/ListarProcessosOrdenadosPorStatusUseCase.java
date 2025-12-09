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

class ListarProcessosOrdenadosPorStatusUseCaseTest {

    private ProcessoGateway processoGateway;
    private ListarProcessosOrdenadosPorStatusUseCase useCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        useCase = new ListarProcessosOrdenadosPorStatusUseCase(processoGateway);
    }

    @Test
    void deveRetornarListaDeProcessosQuandoExistirem() {
        // dado
        UUID idAdvogado = UUID.randomUUID();
        Processo p1 = new Processo();
        Processo p2 = new Processo();

        when(processoGateway.listarProcessosOrdenadosPorStatus(idAdvogado))
                .thenReturn(List.of(p1, p2));

        // quando
        List<ProcessoResponse> resultados = useCase.executar(idAdvogado);

        // então
        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        assertEquals(p1, resultados.get(0).toDomain()); // assumindo método toDomain()
        assertEquals(p2, resultados.get(1).toDomain());
        verify(processoGateway, times(1)).listarProcessosOrdenadosPorStatus(idAdvogado);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremProcessos() {
        // dado
        UUID idAdvogado = UUID.randomUUID();
        when(processoGateway.listarProcessosOrdenadosPorStatus(idAdvogado))
                .thenReturn(List.of());

        // quando
        List<ProcessoResponse> resultados = useCase.executar(idAdvogado);

        // então
        assertNotNull(resultados);
        assertTrue(resultados.isEmpty());
        verify(processoGateway, times(1)).listarProcessosOrdenadosPorStatus(idAdvogado);
    }
}

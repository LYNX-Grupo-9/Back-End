package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ContadorProcessosPorClasseProcessualResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContarProcessosPorClasseProcessualUseCaseTest {

    private ProcessoGateway processoGateway;
    private ContarProcessosPorClasseProcessualUseCase useCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        useCase = new ContarProcessosPorClasseProcessualUseCase(processoGateway);
    }

    @Test
    void deveRetornarMapaQuandoExistiremProcessos() {
        // dado
        UUID idAdvogado = UUID.randomUUID();
        Map<String, Long> mapaContagem = Map.of(
                "Cível", 5L,
                "Trabalhista", 2L
        );

        when(processoGateway.contarProcessosPorClasseProcessualPorAdvogado(idAdvogado))
                .thenReturn(mapaContagem);

        // quando
        ContadorProcessosPorClasseProcessualResponse response = useCase.executar(idAdvogado);

        // então
        assertNotNull(response);
        assertEquals(2, response.getContagemPorClasseProcessual().size());
        assertEquals(5L, response.getContagemPorClasseProcessual().get("Cível"));
        assertEquals(2L, response.getContagemPorClasseProcessual().get("Trabalhista"));
        verify(processoGateway, times(1)).contarProcessosPorClasseProcessualPorAdvogado(idAdvogado);
    }

    @Test
    void deveRetornarMapaVazioQuandoNaoExistiremProcessos() {
        // dado
        UUID idAdvogado = UUID.randomUUID();
        when(processoGateway.contarProcessosPorClasseProcessualPorAdvogado(idAdvogado))
                .thenReturn(Map.of());

        // quando
        ContadorProcessosPorClasseProcessualResponse response = useCase.executar(idAdvogado);

        // então
        assertNotNull(response);
        assertTrue(response.getContagemPorClasseProcessual().isEmpty());
        verify(processoGateway, times(1)).contarProcessosPorClasseProcessualPorAdvogado(idAdvogado);
    }
}

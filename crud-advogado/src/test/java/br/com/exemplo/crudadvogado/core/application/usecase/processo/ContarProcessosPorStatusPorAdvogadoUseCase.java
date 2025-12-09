package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ContadorProcessosPorStatusResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContarProcessosPorStatusPorAdvogadoUseCaseTest {

    private ProcessoGateway processoGateway;
    private ContarProcessosPorStatusPorAdvogadoUseCase useCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        useCase = new ContarProcessosPorStatusPorAdvogadoUseCase(processoGateway);
    }

    @Test
    void deveRetornarContagemQuandoExistiremProcessos() {
        // dado
        UUID idAdvogado = UUID.randomUUID();

        Processo p1 = new Processo();
        p1.setStatus("Em Andamento");

        Processo p2 = new Processo();
        p2.setStatus("Concluído");

        Processo p3 = new Processo();
        p3.setStatus("Em Andamento");

        when(processoGateway.listarPorIdAdvogado(idAdvogado))
                .thenReturn(List.of(p1, p2, p3));

        // quando
        ContadorProcessosPorStatusResponse response = useCase.executar(idAdvogado);

        // então
        Map<String, Long> contagem = response.getContagemPorStatus();
        assertEquals(2, contagem.size());
        assertEquals(2L, contagem.get("Em Andamento"));
        assertEquals(1L, contagem.get("Concluído"));

        verify(processoGateway, times(1)).listarPorIdAdvogado(idAdvogado);
    }

    @Test
    void deveRetornarMapaVazioQuandoNaoExistiremProcessos() {
        // dado
        UUID idAdvogado = UUID.randomUUID();
        when(processoGateway.listarPorIdAdvogado(idAdvogado)).thenReturn(List.of());

        // quando
        ContadorProcessosPorStatusResponse response = useCase.executar(idAdvogado);

        // então
        assertNotNull(response);
        assertTrue(response.getContagemPorStatus().isEmpty());
        verify(processoGateway, times(1)).listarPorIdAdvogado(idAdvogado);
    }
}

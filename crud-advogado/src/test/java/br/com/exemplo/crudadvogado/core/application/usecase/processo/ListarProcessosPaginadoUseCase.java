package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.PageCacheDTO;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarProcessosPaginadoUseCaseTest {

    private ProcessoGateway processoGateway;
    private ListarProcessosPaginadoUseCase useCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        useCase = new ListarProcessosPaginadoUseCase(processoGateway);
    }

    @Test
    void deveRetornarPaginaDeProcessosQuandoExistirem() {
        // dado
        Processo p1 = new Processo();
        Processo p2 = new Processo();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("titulo"));

        Page<Processo> processosPage = new PageImpl<>(List.of(p1, p2), pageable, 2);
        when(processoGateway.listarPaginado(pageable)).thenReturn(processosPage);

        // quando
        PageCacheDTO<?> result = (PageCacheDTO<?>) useCase.executar(pageable);

        // então
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getNumber());
        assertEquals(2, result.getSize());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isFirst());
        assertTrue(result.isLast());
        assertFalse(result.isEmpty());
        verify(processoGateway, times(1)).listarPaginado(pageable);
    }

    @Test
    void deveRetornarPaginaVaziaQuandoNaoExistiremProcessos() {
        // dado
        Pageable pageable = PageRequest.of(0, 2);
        Page<Processo> processosPage = new PageImpl<>(List.of(), pageable, 0);
        when(processoGateway.listarPaginado(pageable)).thenReturn(processosPage);

        // quando
        PageCacheDTO<?> result = (PageCacheDTO<?>) useCase.executar(pageable);

        // então
        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTotalElements());
        verify(processoGateway, times(1)).listarPaginado(pageable);
    }
}

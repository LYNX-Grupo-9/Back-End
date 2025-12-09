package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.exception.ProcessoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExcluirProcessoPorIdUseCaseTest {

    private ProcessoGateway processoGateway;
    private ExcluirProcessoPorIdUseCase useCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        useCase = new ExcluirProcessoPorIdUseCase(processoGateway);
    }

    @Test
    void deveExcluirProcessoQuandoExistir() {
        // dado
        UUID idProcesso = UUID.randomUUID();
        when(processoGateway.existePorId(idProcesso)).thenReturn(true);

        // quando
        useCase.executar(idProcesso);

        // então
        verify(processoGateway, times(1)).existePorId(idProcesso);
        verify(processoGateway, times(1)).excluirPorId(idProcesso);
    }

    @Test
    void deveLancarExcecaoQuandoProcessoNaoExistir() {
        // dado
        UUID idProcesso = UUID.randomUUID();
        when(processoGateway.existePorId(idProcesso)).thenReturn(false);

        // quando / então
        ProcessoNaoEncontradoException exception = assertThrows(
                ProcessoNaoEncontradoException.class,
                () -> useCase.executar(idProcesso)
        );

        assertEquals("Processo não encontrado com ID: " + idProcesso, exception.getMessage());
        verify(processoGateway, times(1)).existePorId(idProcesso);
        verify(processoGateway, never()).excluirPorId(any());
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ProcessoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarProcessoPorIdUseCaseTest {

    private ProcessoGateway processoGateway;
    private BuscarProcessoPorIdUseCase buscarProcessoPorIdUseCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        buscarProcessoPorIdUseCase = new BuscarProcessoPorIdUseCase(processoGateway);
    }

    @Test
    void deveRetornarProcessoQuandoIdExistir() {
        // dado
        UUID id = UUID.randomUUID();
        Processo processo = new Processo(); // configure os campos necessários do processo se houver
        when(processoGateway.buscarPorId(id)).thenReturn(Optional.of(processo));

        // quando
        ProcessoResponse response = buscarProcessoPorIdUseCase.executar(id);

        // então
        assertNotNull(response);
        assertEquals(processo, response.toDomain()); // assumindo que ProcessoResponse tenha método toDomain()
        verify(processoGateway, times(1)).buscarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoProcessoNaoExistir() {
        // dado
        UUID id = UUID.randomUUID();
        when(processoGateway.buscarPorId(id)).thenReturn(Optional.empty());

        // quando / então
        ProcessoNaoEncontradoException exception = assertThrows(
                ProcessoNaoEncontradoException.class,
                () -> buscarProcessoPorIdUseCase.executar(id)
        );

        assertEquals("Processo não encontrado com ID: " + id, exception.getMessage());
        verify(processoGateway, times(1)).buscarPorId(id);
    }
}

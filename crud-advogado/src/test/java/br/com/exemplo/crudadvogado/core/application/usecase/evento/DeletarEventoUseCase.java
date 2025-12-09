package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.exception.EventoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarEventoUseCaseTest {

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private DeletarEventoUseCase useCase;

    private Long idEvento;

    @BeforeEach
    void setup() {
        idEvento = 1L;
    }

    // ============================================
    // ✅ SUCESSO - DELETAR EVENTO
    // ============================================
    @Test
    void deveDeletarEventoComSucesso() {

        when(eventoGateway.buscarPorId(idEvento))
                .thenReturn(Optional.of(mock(Evento.class)));

        doNothing().when(eventoGateway).deletarPorId(idEvento);

        assertDoesNotThrow(() -> useCase.executar(idEvento));

        verify(eventoGateway, times(1)).buscarPorId(idEvento);
        verify(eventoGateway, times(1)).deletarPorId(idEvento);
    }

    // ============================================
    // ❌ ERRO - EVENTO NÃO ENCONTRADO
    // ============================================
    @Test
    void deveLancarExcecaoQuandoEventoNaoExistir() {

        when(eventoGateway.buscarPorId(idEvento))
                .thenReturn(Optional.empty());

        assertThrows(EventoNaoEncontradoException.class,
                () -> useCase.executar(idEvento));

        verify(eventoGateway, times(1)).buscarPorId(idEvento);
        verify(eventoGateway, never()).deletarPorId(anyLong());
    }
}

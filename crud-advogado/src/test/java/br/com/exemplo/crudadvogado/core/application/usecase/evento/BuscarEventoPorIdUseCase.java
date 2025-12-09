package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.EventoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class BuscarEventoPorIdUseCaseTest {

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private BuscarEventoPorIdUseCase useCase;

    private Evento evento;

    @BeforeEach
    void setup() {
        evento = mock(Evento.class);

        when(evento.getIdEvento()).thenReturn(1L);
        when(evento.getNome()).thenReturn("Reunião Teste");
        when(evento.getDescricao()).thenReturn("Descrição Teste");
        when(evento.getLocal()).thenReturn("Sala 1");
        when(evento.getLinkReuniao()).thenReturn("https://meet.com");
        when(evento.getDataReuniao()).thenReturn(null);
        when(evento.getHoraInicio()).thenReturn(LocalTime.of(10, 0));
        when(evento.getHoraFim()).thenReturn(LocalTime.of(11, 0));
        when(evento.getAdvogado()).thenReturn(null);
        when(evento.getCliente()).thenReturn(null);
        when(evento.getCategoria()).thenReturn(3L);
        when(evento.getProcesso()).thenReturn(null);

        when(eventoGateway.buscarPorId(1L))
                .thenReturn(Optional.of(evento));
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveBuscarEventoPorIdComSucesso() {

        // ACT
        EventoResponse response = useCase.executar(1L);

        // ASSERT
        assertNotNull(response);
        assertEquals(1L, response.idEvento());
        assertEquals("Reunião Teste", response.nome());
        assertEquals("Descrição Teste", response.descricao());
        assertEquals("Sala 1", response.local());
        assertEquals("https://meet.com", response.linkReuniao());
        assertEquals(1L, response.advogado());
        assertEquals(2L, response.cliente());
        assertEquals(3L, response.categoria());
        assertEquals(4L, response.processo());

        verify(eventoGateway, times(1))
                .buscarPorId(1L);

        verifyNoMoreInteractions(eventoGateway);
    }

    // ============================================
    // ❌ EVENTO NÃO ENCONTRADO
    // ============================================
    @Test
    void deveLancarExcecaoQuandoEventoNaoExistir() {

        when(eventoGateway.buscarPorId(99L))
                .thenReturn(Optional.empty());

        assertThrows(EventoNaoEncontradoException.class, () ->
                useCase.executar(99L)
        );

        verify(eventoGateway, times(1))
                .buscarPorId(99L);

        verifyNoMoreInteractions(eventoGateway);
    }
}

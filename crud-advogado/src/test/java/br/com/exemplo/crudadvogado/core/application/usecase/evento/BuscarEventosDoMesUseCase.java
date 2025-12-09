package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class BuscarEventosDoMesUseCaseTest {

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private BuscarEventosDoMesUseCase useCase;

    private UUID idAdvogado;

    @BeforeEach
    void setup() {
        idAdvogado = UUID.randomUUID();
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveBuscarEventosDoMesComSucesso() {

        Evento evento1 = mock(Evento.class);
        Evento evento2 = mock(Evento.class);

        List<Evento> eventos = List.of(evento1, evento2);

        when(eventoGateway.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                eq(idAdvogado),
                any(Date.class),
                any(Date.class)
        )).thenReturn(eventos);

        // ACT
        List<EventoResponse> response = useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertEquals(2, response.size());

        verify(eventoGateway, times(1))
                .findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                        eq(idAdvogado),
                        any(Date.class),
                        any(Date.class)
                );

        verifyNoMoreInteractions(eventoGateway);
    }

    // ============================================
    // ✅ SEM EVENTOS NO MÊS
    // ============================================
    @Test
    void deveRetornarListaVaziaQuandoNaoExistirEventosNoMes() {

        when(eventoGateway.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                eq(idAdvogado),
                any(Date.class),
                any(Date.class)
        )).thenReturn(List.of());

        // ACT
        List<EventoResponse> response = useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(eventoGateway, times(1))
                .findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                        eq(idAdvogado),
                        any(Date.class),
                        any(Date.class)
                );

        verifyNoMoreInteractions(eventoGateway);
    }
}

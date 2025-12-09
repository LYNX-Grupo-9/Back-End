package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class BuscarEventosProximosSeteDiasUseCaseTest {

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private BuscarEventosProximosSeteDiasUseCase useCase;

    private UUID idAdvogado;

    @BeforeEach
    void setup() {
        idAdvogado = UUID.randomUUID();
    }

    // ============================================
    // ✅ SUCESSO - RETORNA EVENTOS
    // ============================================
    @Test
    void deveBuscarEventosDosProximosSeteDiasComSucesso() {

        Evento evento1 = mock(Evento.class);
        Evento evento2 = mock(Evento.class);

        when(eventoGateway.buscarPorAdvogadoEPeriodo(
                eq(idAdvogado),
                any(LocalDate.class),
                any(LocalDate.class)
        )).thenReturn(List.of(evento1, evento2));

        // ACT
        List<EventoResponse> response = useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertEquals(2, response.size());

        verify(eventoGateway, times(1))
                .buscarPorAdvogadoEPeriodo(
                        eq(idAdvogado),
                        any(LocalDate.class),
                        any(LocalDate.class)
                );

        verifyNoMoreInteractions(eventoGateway);
    }

    // ============================================
    // ✅ SUCESSO - SEM EVENTOS
    // ============================================
    @Test
    void deveRetornarListaVaziaQuandoNaoHouverEventosNosProximosSeteDias() {

        when(eventoGateway.buscarPorAdvogadoEPeriodo(
                eq(idAdvogado),
                any(LocalDate.class),
                any(LocalDate.class)
        )).thenReturn(List.of());

        // ACT
        List<EventoResponse> response = useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(eventoGateway, times(1))
                .buscarPorAdvogadoEPeriodo(
                        eq(idAdvogado),
                        any(LocalDate.class),
                        any(LocalDate.class)
                );

        verifyNoMoreInteractions(eventoGateway);
    }
}

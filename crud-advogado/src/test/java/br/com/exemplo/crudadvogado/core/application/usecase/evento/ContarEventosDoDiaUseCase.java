package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContarEventosDoDiaUseCaseTest {

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private ContarEventosDoDiaUseCase useCase;

    private UUID idAdvogado;

    @BeforeEach
    void setup() {
        idAdvogado = UUID.randomUUID();
    }

    // ============================================
    // ✅ SUCESSO - DEVE CONTAR EVENTOS DO DIA
    // ============================================
    @Test
    void deveContarEventosDoDiaComSucesso() {

        List<Evento> eventosMock = List.of(
                mock(Evento.class),
                mock(Evento.class),
                mock(Evento.class)
        );

        when(eventoGateway.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                eq(idAdvogado),
                any(Date.class),
                any(Date.class)
        )).thenReturn(eventosMock);

        // ACT
        Map<String, Long> resultado = useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(resultado);
        assertTrue(resultado.containsKey("quantidadeEvento"));
        assertEquals(3L, resultado.get("quantidadeEvento"));

        verify(eventoGateway, times(1))
                .findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                        eq(idAdvogado),
                        any(Date.class),
                        any(Date.class)
                );
    }

    // ============================================
    // ✅ SUCESSO - NENHUM EVENTO NO DIA
    // ============================================
    @Test
    void deveRetornarZeroQuandoNaoExistirEventoNoDia() {

        when(eventoGateway.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                eq(idAdvogado),
                any(Date.class),
                any(Date.class)
        )).thenReturn(Collections.emptyList());

        // ACT
        Map<String, Long> resultado = useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(resultado);
        assertEquals(0L, resultado.get("quantidadeEvento"));

        verify(eventoGateway, times(1))
                .findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                        eq(idAdvogado),
                        any(Date.class),
                        any(Date.class)
                );
    }
}

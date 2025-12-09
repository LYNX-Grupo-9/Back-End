package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.EventoResponse;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarEventosPorClienteUseCaseTest {

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private ListarEventosPorClienteUseCase useCase;

    private UUID idCliente;

    @BeforeEach
    void setup() {
        idCliente = UUID.randomUUID();
    }

    // ============================================
    // ✅ SUCESSO - COM EVENTOS
    // ============================================
    @Test
    void deveListarEventosPorClienteComSucesso() {

        Evento evento1 = mock(Evento.class);
        Evento evento2 = mock(Evento.class);

        when(evento1.getIdEvento()).thenReturn(1L);
        when(evento2.getIdEvento()).thenReturn(2L);

        when(eventoGateway.listarPorCliente(idCliente))
                .thenReturn(List.of(evento1, evento2));

        List<EventoResponse> resposta = useCase.executar(idCliente);

        assertNotNull(resposta);
        assertEquals(2, resposta.size());

        verify(eventoGateway, times(1)).listarPorCliente(idCliente);
    }

    // ============================================
    // ❌ LISTA VAZIA
    // ============================================
    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremEventos() {

        when(eventoGateway.listarPorCliente(idCliente))
                .thenReturn(List.of());

        List<EventoResponse> resposta = useCase.executar(idCliente);

        assertNotNull(resposta);
        assertTrue(resposta.isEmpty());

        verify(eventoGateway, times(1)).listarPorCliente(idCliente);
    }
}

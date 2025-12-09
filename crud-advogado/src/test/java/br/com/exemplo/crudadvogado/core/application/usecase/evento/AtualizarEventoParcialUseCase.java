package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.evento.AtualizarEventoCommand;
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
class AtualizarEventoParcialUseCaseTest {

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private AtualizarEventoParcialUseCase useCase;

    private Evento evento;
    private AtualizarEventoCommand command;

    @BeforeEach
    void setup() {
        evento = mock(Evento.class);

        command = new AtualizarEventoCommand(
                "Reunião teste",
                "Descrição teste",
                "Teams",
                "https://meet.com",
                "24/12/12",
                LocalTime.of(11, 0),
                LocalTime.of(11, 0),
                null,
                null,
                3L,
                null
        );

        when(eventoGateway.buscarPorId(null))
                .thenReturn(Optional.of(evento));

        when(eventoGateway.criar(evento))
                .thenReturn(evento);
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveAtualizarEventoParcialmenteComSucesso() {

        // ACT
        EventoResponse response =
                useCase.executar(1L, command);

        // ASSERT
        assertNotNull(response);

        verify(eventoGateway, times(1))
                .buscarPorId(null);

        verify(evento, times(1)).setNome(command.nome());
        verify(evento, times(1)).setDescricao(command.descricao());
        verify(evento, times(1)).setDataReuniao(command.dataReuniao());
        verify(evento, times(1)).setHoraInicio(command.horaInicio());
        verify(evento, times(1)).setHoraFim(command.horaFim());
        verify(evento, times(1)).setLocal(command.local());
        verify(evento, times(1)).setLinkReuniao(command.linkReuniao());
        verify(evento, times(1)).setCliente(command.idCliente());
        verify(evento, times(1)).setCategoria(command.idCategoria());
        verify(evento, times(1)).setProcesso(command.idProcesso());

        verify(eventoGateway, times(1))
                .criar(evento);

        verifyNoMoreInteractions(eventoGateway, evento);
    }

    // ============================================
    // ❌ EVENTO NÃO ENCONTRADO
    // ============================================
    @Test
    void deveLancarExcecaoQuandoEventoNaoForEncontrado() {

        when(eventoGateway.buscarPorId(null))
                .thenReturn(Optional.empty());

        assertThrows(EventoNaoEncontradoException.class, () ->
                useCase.executar(99L, command)
        );

        verify(eventoGateway, times(1))
                .buscarPorId(null);

        verify(eventoGateway, never())
                .criar(any());

        verifyNoMoreInteractions(eventoGateway);
    }
}

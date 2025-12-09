package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.evento.CriarEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.evento.CriarEventoResponse;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarEventoUseCaseTest {

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private CriarEventoUseCase useCase;

    private CriarEventoCommand command;
    private Evento eventoMock;

    @BeforeEach
    void setup() {
        UUID idAdvogado = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();
        UUID idCategoria = UUID.randomUUID();
        UUID idProcesso = UUID.randomUUID();

        command = new CriarEventoCommand(
                "Reunião Teste",
                "Descrição do evento",
                "Sala 01",
                "https://meet.com/teste",
                new Date(),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                idAdvogado,
                idCliente,
                null,
                idProcesso
        );

        eventoMock = mock(Evento.class);

        when(eventoMock.getIdEvento()).thenReturn(1L);
        when(eventoMock.getNome()).thenReturn(command.nome());
        when(eventoMock.getDescricao()).thenReturn(command.descricao());
        when(eventoMock.getLocal()).thenReturn(command.local());
        when(eventoMock.getLinkReuniao()).thenReturn(command.linkReuniao());
        when(eventoMock.getDataReuniao()).thenReturn(command.dataReuniao());
        when(eventoMock.getHoraInicio()).thenReturn(command.horaInicio());
        when(eventoMock.getHoraFim()).thenReturn(command.horaFim());
        when(eventoMock.getAdvogado()).thenReturn(command.idAdvogado());
        when(eventoMock.getCliente()).thenReturn(command.idCliente());
        when(eventoMock.getCategoria()).thenReturn(command.idCategoria());
        when(eventoMock.getProcesso()).thenReturn(command.idProcesso());

        when(eventoGateway.criar(any(Evento.class))).thenReturn(eventoMock);
    }

    // ============================================
    // ✅ SUCESSO - CRIAR EVENTO
    // ============================================
    @Test
    void deveCriarEventoComSucesso() {

        // ACT
        CriarEventoResponse response = useCase.executar(command);

        // ASSERT
        assertNotNull(response);
        assertEquals(1L, response.idEvento());
        assertEquals(command.nome(), response.nome());
        assertEquals(command.descricao(), response.descricao());
        assertEquals(command.local(), response.local());
        assertEquals(command.linkReuniao(), response.linkReuniao());
        assertEquals(command.dataReuniao(), response.dataReuniao());
        assertEquals(command.horaInicio(), response.horaInicio());
        assertEquals(command.horaFim(), response.horaFim());
        assertEquals(command.idAdvogado(), response.advogado());
        assertEquals(command.idCliente(), response.cliente());
        assertEquals(command.idCategoria(), response.categoria());
        assertEquals(command.idProcesso(), response.processo());

        verify(eventoGateway, times(1)).criar(any(Evento.class));
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento.CriarSolicitacaoAgendamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.message.SolicitacaoAgendamentoMessage;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.CriarSolicitacaoAgendamentoResponse;
import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.RabbitMQProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class CriarSolicitacaoAgendamentoUseCaseTest {

    @Mock
    private SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway;

    @Mock
    private RabbitMQProducerService rabbitMQProducerService;

    @InjectMocks
    private CriarSolicitacaoAgendamentoUseCase useCase;

    private CriarSolicitacaoAgendamentoCommand command;
    private SolicitacaoAgendamento solicitacao;

    @BeforeEach
    void setup() {
        UUID idAdvogado = UUID.randomUUID();
        UUID idSolicitacao = UUID.randomUUID();

        command = new CriarSolicitacaoAgendamentoCommand(
                "João da Silva",
                "11999999999",
                "joao@email.com",
                "Consulta",
                LocalDate.now(),
                LocalTime.of(10, 30),
                idAdvogado
        );

        solicitacao = mock(SolicitacaoAgendamento.class);

        when(solicitacao.getIdSolicitacaoAgendamento()).thenReturn(null);
        when(solicitacao.getNome()).thenReturn("João da Silva");
        when(solicitacao.getTelefone()).thenReturn("11999999999");
        when(solicitacao.getEmail()).thenReturn("joao@email.com");
        when(solicitacao.getAssunto()).thenReturn("Consulta");
        when(solicitacao.getDataSolicitacao()).thenReturn(null);
        when(solicitacao.getHoraSolicitacao()).thenReturn(command.horaSolicitacao());
        when(solicitacao.getIdAdvogado()).thenReturn(idAdvogado);
    }

    // ============================================
    // ✅ SUCESSO - CRIAR SOLICITAÇÃO
    // ============================================
    @Test
    void deveCriarSolicitacaoEEnviarMensagemParaRabbitMQ() {
        // ARRANGE
        when(solicitacaoAgendamentoGateway.criar(any()))
                .thenReturn(solicitacao);

        ArgumentCaptor<SolicitacaoAgendamentoMessage> messageCaptor =
                ArgumentCaptor.forClass(SolicitacaoAgendamentoMessage.class);

        // ACT
        CriarSolicitacaoAgendamentoResponse response =
                useCase.executar(command);

        // ASSERT
        assertNotNull(response);
        assertEquals(solicitacao.getIdSolicitacaoAgendamento(), response.idSolicitacao());
        assertEquals("João da Silva", response.nome());
        assertEquals("11999999999", response.telefone());
        assertEquals("joao@email.com", response.email());
        assertEquals("Consulta", response.assunto());
        assertEquals(command.dataSolicitacao(), response.dataSolicitacao());
        assertEquals(command.horaSolicitacao(), response.horaSolicitacao());
        assertEquals(command.idAdvogado(), response.idAdvogado());

        verify(solicitacaoAgendamentoGateway, times(1))
                .criar(any(SolicitacaoAgendamento.class));

        verify(rabbitMQProducerService, times(1))
                .enviarSolicitacaoAgendamento(messageCaptor.capture());

        SolicitacaoAgendamentoMessage message = messageCaptor.getValue();

        assertEquals(solicitacao.getIdSolicitacaoAgendamento(), message.getIdSolicitacao());
        assertEquals("João da Silva", message.getNome());
        assertEquals("Consulta", message.getAssunto());
        assertEquals("CRIADA", message.getStatus());

        verifyNoMoreInteractions(solicitacaoAgendamentoGateway, rabbitMQProducerService);
    }
}

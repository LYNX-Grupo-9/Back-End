package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento.MarcarComoVisualizadoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.MarcarComoVisualizadoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.SolicitacaoAgendamentoNaoEncontradaException;
import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class MarcarComoVisualizadoUseCaseTest {

    @Mock
    private SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway;

    @InjectMocks
    private MarcarComoVisualizadoUseCase useCase;

    private SolicitacaoAgendamento solicitacao;
    private MarcarComoVisualizadoCommand command;

    @BeforeEach
    void setup() {
        Long idSolicitacao = 1L;

        command = new MarcarComoVisualizadoCommand(idSolicitacao);

        solicitacao = mock(SolicitacaoAgendamento.class);

        when(solicitacaoAgendamentoGateway.buscarPorId(idSolicitacao))
                .thenReturn(Optional.of(solicitacao));

        when(solicitacaoAgendamentoGateway.atualizar(solicitacao))
                .thenReturn(solicitacao);
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveMarcarSolicitacaoComoVisualizadaComSucesso() {
        // ACT
        MarcarComoVisualizadoResponse response =
                useCase.executar(command);

        // ASSERT
        assertNotNull(response);

        verify(solicitacaoAgendamentoGateway, times(1))
                .buscarPorId(command.idSolicitacao());

        verify(solicitacao, times(1))
                .marcarComoVisualizado();

        verify(solicitacaoAgendamentoGateway, times(1))
                .atualizar(solicitacao);

        verifyNoMoreInteractions(solicitacaoAgendamentoGateway, solicitacao);
    }

    // ============================================
    // ❌ SOLICITAÇÃO NÃO ENCONTRADA
    // ============================================
    @Test
    void deveLancarExcecaoQuandoSolicitacaoNaoForEncontrada() {
        // ARRANGE
        Long idInexistente = 999L;
        MarcarComoVisualizadoCommand cmdInvalido =
                new MarcarComoVisualizadoCommand(idInexistente);

        when(solicitacaoAgendamentoGateway.buscarPorId(idInexistente))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(SolicitacaoAgendamentoNaoEncontradaException.class, () ->
                useCase.executar(cmdInvalido)
        );

        verify(solicitacaoAgendamentoGateway, times(1))
                .buscarPorId(idInexistente);

        verify(solicitacaoAgendamentoGateway, never())
                .atualizar(any());

        verifyNoMoreInteractions(solicitacaoAgendamentoGateway);
    }
}

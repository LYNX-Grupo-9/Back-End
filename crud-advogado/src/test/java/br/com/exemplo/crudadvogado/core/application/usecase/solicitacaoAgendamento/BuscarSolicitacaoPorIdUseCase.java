package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.MarcarComoVisualizadoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.SolicitacaoAgendamentoNaoEncontradaException;
import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;
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
class BuscarSolicitacaoPorIdUseCaseTest {

    @Mock
    private SolicitacaoAgendamentoGateway gateway;

    @InjectMocks
    private BuscarSolicitacaoPorIdUseCase useCase;

    private Long idSolicitacao;
    private SolicitacaoAgendamento solicitacao;

    @BeforeEach
    void setup() {
        idSolicitacao = 1L;
        solicitacao = mock(SolicitacaoAgendamento.class);
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveBuscarSolicitacaoPorIdComSucesso() {
        // ARRANGE
        when(gateway.buscarPorId(idSolicitacao))
                .thenReturn(Optional.of(solicitacao));

        // ACT
        MarcarComoVisualizadoResponse response =
                useCase.executar(idSolicitacao);

        // ASSERT
        assertNotNull(response);
        assertNotNull(response.solicitacao());

        verify(gateway, times(1))
                .buscarPorId(idSolicitacao);
    }

    // ============================================
    // ❌ ERRO - SOLICITAÇÃO NÃO ENCONTRADA
    // ============================================
    @Test
    void deveLancarExcecaoQuandoSolicitacaoNaoForEncontrada() {
        // ARRANGE
        when(gateway.buscarPorId(idSolicitacao))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        SolicitacaoAgendamentoNaoEncontradaException exception =
                assertThrows(SolicitacaoAgendamentoNaoEncontradaException.class,
                        () -> useCase.executar(idSolicitacao));

        assertEquals(
                "Solicitação não encontrada com ID: " + idSolicitacao,
                exception.getMessage()
        );

        verify(gateway, times(1))
                .buscarPorId(idSolicitacao);
    }
}

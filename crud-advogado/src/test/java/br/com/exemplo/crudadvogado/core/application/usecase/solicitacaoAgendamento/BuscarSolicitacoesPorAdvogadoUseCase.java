package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.MarcarComoVisualizadoResponse;
import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;
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
class BuscarSolicitacoesPorAdvogadoUseCaseTest {

    @Mock
    private SolicitacaoAgendamentoGateway gateway;

    @InjectMocks
    private BuscarSolicitacoesPorAdvogadoUseCase useCase;

    private UUID idAdvogado;

    @BeforeEach
    void setup() {
        idAdvogado = UUID.randomUUID();
    }

    // ============================================
    // ✅ SUCESSO - LISTAR SOLICITAÇÕES DO ADVOGADO
    // ============================================
    @Test
    void deveBuscarSolicitacoesPorAdvogadoComSucesso() {
        // ARRANGE
        List<SolicitacaoAgendamento> solicitacoes =
                List.of(
                        mock(SolicitacaoAgendamento.class),
                        mock(SolicitacaoAgendamento.class)
                );

        when(gateway.buscarPorAdvogado(idAdvogado))
                .thenReturn(solicitacoes);

        // ACT
        List<MarcarComoVisualizadoResponse> response =
                useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertEquals(2, response.size());

        verify(gateway, times(1))
                .buscarPorAdvogado(idAdvogado);
    }

    // ============================================
    // ✅ LISTA VAZIA
    // ============================================
    @Test
    void deveRetornarListaVaziaQuandoNaoHouverSolicitacoes() {
        // ARRANGE
        when(gateway.buscarPorAdvogado(idAdvogado))
                .thenReturn(List.of());

        // ACT
        List<MarcarComoVisualizadoResponse> response =
                useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(gateway, times(1))
                .buscarPorAdvogado(idAdvogado);
    }
}

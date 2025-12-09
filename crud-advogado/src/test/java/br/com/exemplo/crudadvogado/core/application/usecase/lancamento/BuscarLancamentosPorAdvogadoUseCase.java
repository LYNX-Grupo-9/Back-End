package br.com.exemplo.crudadvogado.core.application.usecase.lancamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LancamentoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.lancamento.LancamentoComParcelasResponse;
import br.com.exemplo.crudadvogado.core.domain.Lancamento;
import br.com.exemplo.crudadvogado.core.domain.Parcela;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.parcela.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarLancamentosPorAdvogadoUseCaseTest {

    @Mock
    private LancamentoGateway lancamentoGateway;

    @Mock
    private ProcessoGateway processoGateway;

    @InjectMocks
    private BuscarLancamentosPorAdvogadoUseCase useCase;

    private UUID idAdvogado;
    private UUID idProcesso;
    private UUID idLancamento;
    private UUID idCliente;

    private Processo processo;
    private Lancamento lancamento;
    private Parcela parcela;

    @BeforeEach
    void setup() {
        idAdvogado = UUID.randomUUID();
        idProcesso = UUID.randomUUID();
        idLancamento = UUID.randomUUID();
        idCliente = UUID.randomUUID();

        processo = new Processo();
        processo.setIdProcesso(idProcesso);

        lancamento = new Lancamento();
        lancamento.setIdLancamento(null);
        lancamento.setIdCliente(idCliente);
        lancamento.setIdProcesso(idProcesso);
        lancamento.setTitulo("Honorários");

        parcela = new Parcela();
        parcela.setIdParcela(null);
        parcela.setIdLancamento(null);
        parcela.setValor(new BigDecimal("250.00"));
        parcela.setDataVencimento(LocalDate.now().plusDays(10));
        parcela.setStatus(Status.criar("PENDENTE"));
    }

    // ============================================
    // ✅ SUCESSO — PROCESSOS, LANÇAMENTOS E PARCELAS
    // ============================================
    @Test
    void deveBuscarLancamentosComParcelasPorAdvogadoComSucesso() {
        // ARRANGE
        when(processoGateway.buscarPorAdvogado(idAdvogado))
                .thenReturn(List.of(processo));

        when(lancamentoGateway.buscarPorProcessos(List.of(idProcesso)))
                .thenReturn(List.of(lancamento));

        when(lancamentoGateway.buscarParcelasPorLancamento(null))
                .thenReturn(List.of(parcela));

        // ACT
        List<LancamentoComParcelasResponse> response =
                useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertEquals(1, response.size());

        LancamentoComParcelasResponse lancamentoResponse = response.get(0);

        assertEquals(idLancamento, lancamentoResponse.idLancamento());
        assertEquals(idCliente, lancamentoResponse.idCliente());
        assertEquals(idProcesso, lancamentoResponse.idProcesso());
        assertEquals("Honorários", lancamentoResponse.titulo());
        assertEquals(1, lancamentoResponse.parcelas().size());

        var parcelaResponse = lancamentoResponse.parcelas().get(0);

        assertEquals(parcela.getIdParcela(), parcelaResponse.idParcela());
        assertEquals(parcela.getIdLancamento(), parcelaResponse.idLancamento());
        assertEquals(parcela.getValor(), parcelaResponse.valor());
        assertEquals(parcela.getDataVencimento(), parcelaResponse.dataVencimento());
        assertEquals(parcela.getStatus(), parcelaResponse.status());

        verify(processoGateway, times(1)).buscarPorAdvogado(idAdvogado);
        verify(lancamentoGateway, times(1))
                .buscarPorProcessos(List.of(idProcesso));
        verify(lancamentoGateway, times(1))
                .buscarParcelasPorLancamento(null);
    }

    // ============================================
    // ✅ QUANDO NÃO EXISTEM PROCESSOS
    // ============================================
    @Test
    void deveRetornarListaVaziaQuandoAdvogadoNaoPossuirProcessos() {
        // ARRANGE
        when(processoGateway.buscarPorAdvogado(idAdvogado))
                .thenReturn(List.of());

        // ACT
        List<LancamentoComParcelasResponse> response =
                useCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(processoGateway, times(1)).buscarPorAdvogado(idAdvogado);
        verify(lancamentoGateway, never()).buscarPorProcessos(any());
        verify(lancamentoGateway, never()).buscarParcelasPorLancamento(any());
    }
}

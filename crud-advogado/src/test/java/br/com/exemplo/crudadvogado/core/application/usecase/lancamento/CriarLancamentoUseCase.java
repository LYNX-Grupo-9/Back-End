package br.com.exemplo.crudadvogado.core.application.usecase.lancamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LancamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.lancamento.CriarLancamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.lancamento.CriarLancamentoResponse;
import br.com.exemplo.crudadvogado.core.domain.Lancamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarLancamentoUseCaseTest {

    @Mock
    private LancamentoGateway lancamentoGateway;

    @InjectMocks
    private CriarLancamentoUseCase useCase;

    private UUID idCliente;
    private UUID idProcesso;
    private UUID idLancamento;

    private CriarLancamentoCommand command;
    private Lancamento lancamentoCriado;

    @BeforeEach
    void setup() {
        idCliente = UUID.randomUUID();
        idProcesso = UUID.randomUUID();
        idLancamento = UUID.randomUUID();

        command = new CriarLancamentoCommand(
                "Honorários",
                idProcesso,
                idCliente
        );

        lancamentoCriado = mock(Lancamento.class);

        when(lancamentoCriado.getIdLancamento()).thenReturn(null);
        when(lancamentoCriado.getTitulo()).thenReturn("Honorários");
        when(lancamentoCriado.getIdProcesso()).thenReturn(idProcesso);
        when(lancamentoCriado.getIdCliente()).thenReturn(idCliente);
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveCriarLancamentoComSucesso() {
        // ARRANGE
        when(lancamentoGateway.criar(any(Lancamento.class)))
                .thenReturn(lancamentoCriado);

        // ACT
        CriarLancamentoResponse response =
                useCase.executar(command);

        // ASSERT
        assertNotNull(response);
        assertEquals(idLancamento, response.idLancamento());
        assertEquals("Honorários", response.titulo());
        assertEquals(idProcesso, response.idProcesso());
        assertEquals(idCliente, response.idCliente());

        verify(lancamentoGateway, times(1))
                .criar(any(Lancamento.class));
    }

    // ============================================
    // ❌ ERRO - idCliente NULL
    // ============================================
    @Test
    void deveLancarErroQuandoIdClienteForNulo() {
        CriarLancamentoCommand commandInvalido =
                new CriarLancamentoCommand(
                        "Honorários",
                        idProcesso,
                        null
                );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> useCase.executar(commandInvalido)
        );

        assertEquals(
                "Deve informar tanto idCliente quanto idProcesso",
                exception.getMessage()
        );

        verify(lancamentoGateway, never()).criar(any());
    }

    // ============================================
    // ❌ ERRO - idProcesso NULL
    // ============================================
    @Test
    void deveLancarErroQuandoIdProcessoForNulo() {
        CriarLancamentoCommand commandInvalido =
                new CriarLancamentoCommand(
                        "Honorários",
                        null,
                        idCliente
                );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> useCase.executar(commandInvalido)
        );

        assertEquals(
                "Deve informar tanto idCliente quanto idProcesso",
                exception.getMessage()
        );

        verify(lancamentoGateway, never()).criar(any());
    }
}

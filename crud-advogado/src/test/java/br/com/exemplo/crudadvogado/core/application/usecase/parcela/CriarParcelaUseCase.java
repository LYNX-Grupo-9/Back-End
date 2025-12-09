package br.com.exemplo.crudadvogado.core.application.usecase.parcela;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.parcela.CriarParcelaCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.parcela.CriarParcelaResponse;
import br.com.exemplo.crudadvogado.core.domain.Parcela;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarParcelaUseCaseTest {

    @Mock
    private ParcelaGateway parcelaGateway;

    @InjectMocks
    private CriarParcelaUseCase useCase;

    private CriarParcelaCommand command;
    private Parcela parcelaCriada;

    private UUID idParcela;
    private UUID idLancamento;

    @BeforeEach
    void setup() {
        idParcela = UUID.randomUUID();
        idLancamento = UUID.randomUUID();

        command = new CriarParcelaCommand(
                new BigDecimal("250.00"),
                null,
                null,
                null
        );

        parcelaCriada = mock(Parcela.class);

        when(parcelaCriada.getIdParcela()).thenReturn(null);
        when(parcelaCriada.getValor()).thenReturn(new BigDecimal("250.00"));
        when(parcelaCriada.getDataVencimento()).thenReturn(command.dataVencimento());
        when(parcelaCriada.getStatus()).thenReturn(null);
        when(parcelaCriada.getIdLancamento()).thenReturn(null);
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveCriarParcelaComSucesso() {
        // ARRANGE
        when(parcelaGateway.criar(any(Parcela.class)))
                .thenReturn(parcelaCriada);

        // ACT
        CriarParcelaResponse response = useCase.executar(command);

        // ASSERT
        assertNotNull(response);
        assertEquals(idParcela, response.idParcela());
        assertEquals(new BigDecimal("250.00"), response.valor());
        assertEquals(command.dataVencimento(), response.dataVencimento());
        assertEquals("PENDENTE", response.status());
        assertEquals(idLancamento, response.idLancamento());

        verify(parcelaGateway, times(1))
                .criar(any(Parcela.class));
    }
}

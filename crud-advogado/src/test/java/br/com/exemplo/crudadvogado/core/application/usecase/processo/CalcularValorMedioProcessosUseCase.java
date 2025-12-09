package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ValorMedioProcessosResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalcularValorMedioProcessosUseCaseTest {

    private ProcessoGateway processoGateway;
    private CalcularValorMedioProcessosUseCase calcularValorMedioUseCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        calcularValorMedioUseCase = new CalcularValorMedioProcessosUseCase(processoGateway);
    }

    @Test
    void deveRetornarValorMedioQuandoExistir() {
        // dado
        UUID idAdvogado = UUID.randomUUID();
        when(processoGateway.calcularValorMedioPorAdvogado(idAdvogado)).thenReturn(5000.0);

        // quando
        ValorMedioProcessosResponse response = calcularValorMedioUseCase.executar(idAdvogado);

        // então
        assertNotNull(response);
        assertEquals(5000.0, response.getValorMedio());
        verify(processoGateway, times(1)).calcularValorMedioPorAdvogado(idAdvogado);
    }

    @Test
    void deveRetornarZeroQuandoValorMedioForNulo() {
        // dado
        UUID idAdvogado = UUID.randomUUID();
        when(processoGateway.calcularValorMedioPorAdvogado(idAdvogado)).thenReturn(null);

        // quando
        ValorMedioProcessosResponse response = calcularValorMedioUseCase.executar(idAdvogado);

        // então
        assertNotNull(response);
        assertEquals(0.0, response.getValorMedio());
        verify(processoGateway, times(1)).calcularValorMedioPorAdvogado(idAdvogado);
    }
}

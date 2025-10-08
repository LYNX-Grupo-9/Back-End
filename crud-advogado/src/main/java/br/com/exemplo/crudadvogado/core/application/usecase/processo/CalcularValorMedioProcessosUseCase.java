package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ValorMedioProcessosResponse;

import java.util.UUID;

public class CalcularValorMedioProcessosUseCase {
    private final ProcessoGateway processoGateway;

    public CalcularValorMedioProcessosUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public ValorMedioProcessosResponse executar(UUID idAdvogado) {
        Double valorMedio = processoGateway.calcularValorMedioPorAdvogado(idAdvogado);

        Double valorFinal = valorMedio != null ? valorMedio : 0.0;

        return new ValorMedioProcessosResponse(valorFinal);
    }
}

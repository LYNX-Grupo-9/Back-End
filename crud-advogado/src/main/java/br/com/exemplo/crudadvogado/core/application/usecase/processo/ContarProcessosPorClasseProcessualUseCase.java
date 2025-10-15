package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ContadorProcessosPorClasseProcessualResponse;

import java.util.Map;
import java.util.UUID;

public class ContarProcessosPorClasseProcessualUseCase {

    private final ProcessoGateway processoGateway;

    public ContarProcessosPorClasseProcessualUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public ContadorProcessosPorClasseProcessualResponse executar(UUID idAdvogado) {
        Map<String, Long> contagemPorClasseProcessual =
                processoGateway.contarProcessosPorClasseProcessualPorAdvogado(idAdvogado);

        return new ContadorProcessosPorClasseProcessualResponse(contagemPorClasseProcessual);
    }

}

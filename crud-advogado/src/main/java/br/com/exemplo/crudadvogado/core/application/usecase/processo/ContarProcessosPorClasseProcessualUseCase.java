package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;

import java.util.Map;
import java.util.UUID;

public class ContarProcessosPorClasseProcessualUseCase {

    private final ProcessoGateway processoGateway;

    public ContarProcessosPorClasseProcessualUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public Map<String, Long> executar(UUID idAdvogado) {
        return processoGateway.contarProcessosPorClasseProcessualPorAdvogado(idAdvogado);
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ContadorProcessosPorStatusResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ContarProcessosPorStatusPorAdvogadoUseCase {

    private final ProcessoGateway processoGateway;

    public ContarProcessosPorStatusPorAdvogadoUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public ContadorProcessosPorStatusResponse executar(UUID idAdvogado) {
        var processos = processoGateway.listarPorIdAdvogado(idAdvogado);

        Map<String, Long> contagemPorStatus = processos.stream()
                .collect(Collectors.groupingBy(
                        Processo::getStatus,
                        Collectors.counting()
                ));

        return new ContadorProcessosPorStatusResponse(contagemPorStatus);
    }
}

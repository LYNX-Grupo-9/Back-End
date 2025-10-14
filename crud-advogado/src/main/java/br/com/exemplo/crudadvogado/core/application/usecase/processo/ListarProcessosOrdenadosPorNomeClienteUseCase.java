package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ListarProcessosOrdenadosPorNomeClienteUseCase {

    private final ProcessoGateway processoGateway;

    public ListarProcessosOrdenadosPorNomeClienteUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public List<ProcessoResponse> executar(UUID idAdvogado) {
        List<Processo> processos = processoGateway.listarProcessosOrdenadosPorNomeCliente(idAdvogado);

        return processos.stream()
                .map(ProcessoResponse::new)
                .collect(Collectors.toList());
    }
}

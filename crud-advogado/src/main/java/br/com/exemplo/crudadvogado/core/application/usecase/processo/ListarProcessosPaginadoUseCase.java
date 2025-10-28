package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListarProcessosPaginadoUseCase {
    private final ProcessoGateway processoGateway;

    public ListarProcessosPaginadoUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public Page<ProcessoResponse> executar(Pageable pageable) {
        Page<Processo> processos = processoGateway.listarPaginado(pageable);
        return processos.map(ProcessoResponse::new);
    }
}

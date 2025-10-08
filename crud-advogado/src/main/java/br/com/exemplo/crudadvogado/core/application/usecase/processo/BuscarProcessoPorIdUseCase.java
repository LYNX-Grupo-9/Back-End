package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ProcessoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Processo;

public class BuscarProcessoPorIdUseCase {
    private final ProcessoGateway processoGateway;

    public BuscarProcessoPorIdUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public ProcessoResponse executar(Long idProcesso) {
        Processo processo = processoGateway.buscarPorId(idProcesso)
                .orElseThrow(() -> new ProcessoNaoEncontradoException("Processo não encontrado com ID: " + idProcesso));

        return new ProcessoResponse(processo);
    }
}

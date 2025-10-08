package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.exception.ProcessoNaoEncontradoException;

import java.util.UUID;

public class ExcluirProcessoPorIdUseCase {
    private final ProcessoGateway processoGateway;

    public ExcluirProcessoPorIdUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public void executar(UUID idProcesso) {
        boolean processoExiste = processoGateway.existePorId(idProcesso);

        if (!processoExiste) {
            throw new ProcessoNaoEncontradoException("Processo não encontrado com ID: " + idProcesso);
        }

        processoGateway.excluirPorId(idProcesso);
    }
}

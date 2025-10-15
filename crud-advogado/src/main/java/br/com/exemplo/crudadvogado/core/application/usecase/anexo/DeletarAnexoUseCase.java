package br.com.exemplo.crudadvogado.core.application.usecase.anexo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;

public class DeletarAnexoUseCase {
    private final AnexoGateway anexoGateway;

    public DeletarAnexoUseCase(AnexoGateway anexoGateway) {
        this.anexoGateway = anexoGateway;
    }

    public void executar(String idItem) {
        if (!anexoGateway.existePorIdItem(idItem)) {
            throw new RuntimeException("Anexo com ID " + idItem + " não encontrado.");
        }
        anexoGateway.deletarPorIdItem(idItem);
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.anexo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.anexo.AnexoResponse;
import br.com.exemplo.crudadvogado.core.domain.Anexo;

import java.util.List;
import java.util.UUID;

public class BuscarAnexoUseCase {
    private final AnexoGateway anexoGateway;

    public BuscarAnexoUseCase(AnexoGateway anexoGateway) {
        this.anexoGateway = anexoGateway;
    }

    public List<AnexoResponse> buscarPorIdCliente(UUID idCliente) {
        List<Anexo> anexos = anexoGateway.buscarPorIdCliente(idCliente);
        return anexos.stream()
                .map(anexo -> new AnexoResponse(
                        anexo.getIdAnexo(),
                        anexo.getNomeAnexo(),
                        anexo.getIdItem(),
                        anexo.getIdCliente(),
                        anexo.getIdProcesso()
                ))
                .toList();
    }

    public List<AnexoResponse> buscarPorIdProcesso(UUID idProcesso) {
        List<Anexo> anexos = anexoGateway.buscarPorIdProcesso(idProcesso);
        return anexos.stream()
                .map(anexo -> new AnexoResponse(
                        anexo.getIdAnexo(),
                        anexo.getNomeAnexo(),
                        anexo.getIdItem(),
                        anexo.getIdCliente(),
                        anexo.getIdProcesso()
                ))
                .toList();
    }
}

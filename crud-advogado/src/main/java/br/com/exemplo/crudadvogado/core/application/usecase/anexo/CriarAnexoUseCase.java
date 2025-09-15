package br.com.exemplo.crudadvogado.core.application.usecase.anexo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.anexo.CriarAnexoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.anexo.CriarAnexoResponse;
import br.com.exemplo.crudadvogado.core.domain.Anexo;

public class CriarAnexoUseCase {

    private final AnexoGateway anexoGateway;

    public CriarAnexoUseCase(AnexoGateway anexoGateway) {
        this.anexoGateway = anexoGateway;
    }

    public CriarAnexoResponse executar(CriarAnexoCommand command) {
        Anexo anexoParaCriar  = Anexo.criarNovo(
                command.nomeAnexo(),
                command.idItem(),
                command.idCliente(),
                command.idProcesso()
        );

        Anexo anexoCriado = anexoGateway.criar(anexoParaCriar);

        return new  CriarAnexoResponse(
                anexoParaCriar.getIdAnexo(),
                anexoCriado.getNomeAnexo(),
                anexoParaCriar.getIdItem(),
                anexoCriado.getIdCliente(),
                anexoCriado.getIdProcesso()
        );
    }
}

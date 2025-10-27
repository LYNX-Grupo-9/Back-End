package br.com.exemplo.crudadvogado.core.application.usecase.anexo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.anexo.CriarAnexoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.anexo.CriarAnexoResponse;
import br.com.exemplo.crudadvogado.core.domain.Anexo;

import java.util.UUID;

public class CriarAnexoUseCase {

    private final AnexoGateway anexoGateway;

    public CriarAnexoUseCase(AnexoGateway anexoGateway) {
        this.anexoGateway = anexoGateway;
    }

    public CriarAnexoResponse executar(CriarAnexoCommand command) {

        validarIdsClienteProcesso(command.idCliente(), command.idProcesso());

        Anexo anexoParaCriar = Anexo.criarNovo(
                command.nomeAnexo(),
                command.idItem(),
                command.idCliente(),
                command.idProcesso()
        );

        Anexo anexoCriado = anexoGateway.criar(anexoParaCriar);

        return new CriarAnexoResponse(
                anexoCriado.getIdAnexo(),
                anexoCriado.getNomeAnexo(),
                anexoCriado.getIdItem(),
                anexoCriado.getIdCliente(),
                anexoCriado.getIdProcesso()
        );
    }

    private void validarIdsClienteProcesso(UUID idCliente, UUID idProcesso) {
        boolean temIdCliente = idCliente != null;
        boolean temIdProcesso = idProcesso != null;

        if (!temIdCliente && !temIdProcesso) {
            throw new RuntimeException("Deve informar idCliente ou idProcesso");
        }

        if (temIdCliente && temIdProcesso) {
            throw new RuntimeException("Informe apenas idCliente ou idProcesso, não ambos");
        }
    }
}
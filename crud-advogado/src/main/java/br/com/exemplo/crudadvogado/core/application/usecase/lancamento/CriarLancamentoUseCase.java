package br.com.exemplo.crudadvogado.core.application.usecase.lancamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LancamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.lancamento.CriarLancamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.lancamento.CriarLancamentoResponse;
import br.com.exemplo.crudadvogado.core.domain.Lancamento;

import java.util.UUID;

public class CriarLancamentoUseCase {

    private final LancamentoGateway lancamentoGateway;

    public CriarLancamentoUseCase(LancamentoGateway lancamentoGateway) {
        this.lancamentoGateway = lancamentoGateway;
    }

    public CriarLancamentoResponse executar(CriarLancamentoCommand command){
        UUID idCliente = command.idCliente();
        UUID idProcesso = command.idProcesso();

        validarIdsClienteProcesso(idCliente, idProcesso);

        Lancamento lancamentoParaCriar = Lancamento.criarNovo(
                command.titulo(),
                command.idProcesso(),
                command.idCliente()
        );

        Lancamento lancamentoCriado = lancamentoGateway.criar(lancamentoParaCriar);

        return new CriarLancamentoResponse(
                lancamentoCriado.getIdLancamento(),
                lancamentoCriado.getTitulo(),
                lancamentoCriado.getIdProcesso(),
                lancamentoCriado.getIdCliente()
        );
    }

    private void validarIdsClienteProcesso(UUID idCliente, UUID idProcesso) {
        boolean temIdCliente = idCliente != null;
        boolean temIdProcesso = idProcesso != null;

        if (!temIdCliente || !temIdProcesso) {
            throw new RuntimeException("Deve informar tanto idCliente quanto idProcesso");
        }
    }
}

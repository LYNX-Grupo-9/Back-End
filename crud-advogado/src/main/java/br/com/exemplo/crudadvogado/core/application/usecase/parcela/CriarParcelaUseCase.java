package br.com.exemplo.crudadvogado.core.application.usecase.parcela;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.parcela.CriarParcelaCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.parcela.CriarParcelaResponse;
import br.com.exemplo.crudadvogado.core.domain.Parcela;

public class CriarParcelaUseCase {

    private final ParcelaGateway parcelaGateway;

    public CriarParcelaUseCase(ParcelaGateway parcelaGateway) {
        this.parcelaGateway = parcelaGateway;
    }

    public CriarParcelaResponse executar(CriarParcelaCommand command){
        Parcela parcelaParaCriar = Parcela.criarNovo(
                command.valor(),
                command.dataVencimento(),
                command.status(),
                command.idLancamento()
        );

        Parcela parcelaCriada = parcelaGateway.criar(parcelaParaCriar);

        return new CriarParcelaResponse(
                parcelaCriada.getIdParcela(),
                parcelaCriada.getValor(),
                parcelaCriada.getDataVencimento(),
                parcelaCriada.getStatus(),
                parcelaCriada.getIdLancamento()
        );
    }

}

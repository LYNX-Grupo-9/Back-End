package br.com.exemplo.crudadvogado.core.application.usecase.parcela;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.parcela.AtualizarParcelaCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.parcela.ParcelaResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ParcelaNaoEncontradaException;
import br.com.exemplo.crudadvogado.core.domain.Parcela;

public class AtualizarParcelaParcialmenteUseCase {

    private final ParcelaGateway parcelaGateway;

    public AtualizarParcelaParcialmenteUseCase(ParcelaGateway parcelaGateway) {
        this.parcelaGateway = parcelaGateway;
    }

    public ParcelaResponse executar(Long id, AtualizarParcelaCommand request) {
        Parcela parcela = parcelaGateway.buscarPorId(id)
                .orElseThrow(() -> new ParcelaNaoEncontradaException("Parcela não encontrada"));

        atualizarCampos(parcela, request);

        Parcela parcelaAtualizada = parcelaGateway.atualizar(parcela);
        return new ParcelaResponse(parcelaAtualizada);
    }

    private void atualizarCampos(Parcela parcela, AtualizarParcelaCommand request) {
        if (request.valor() != null) {
            parcela.setValor(request.valor());
        }
        if (request.dataVencimento() != null) {
            parcela.setDataVencimento(request.dataVencimento());
        }
        if (request.status() != null) {
            parcela.setStatus(request.status());
        }
        if (request.idLancamento() != null) {
            parcela.setIdLancamento(request.idLancamento());
        }
    }
}

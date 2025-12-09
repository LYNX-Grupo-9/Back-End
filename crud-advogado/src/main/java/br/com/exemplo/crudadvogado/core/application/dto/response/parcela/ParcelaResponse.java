package br.com.exemplo.crudadvogado.core.application.dto.response.parcela;

import br.com.exemplo.crudadvogado.core.domain.Parcela;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.parcela.Status;

import java.math.BigDecimal;
import java.util.Date;

public record ParcelaResponse(

        Long idParcela,
        BigDecimal valor,
        Date dataVencimento,
        Status status,
        Long idLancamento

) {
    public ParcelaResponse(Parcela parcela) {
        this(
                parcela.getIdParcela(),
                parcela.getValor(),
                parcela.getDataVencimento(),
                parcela.getStatus(),
                parcela.getIdLancamento()
        );
    }
}

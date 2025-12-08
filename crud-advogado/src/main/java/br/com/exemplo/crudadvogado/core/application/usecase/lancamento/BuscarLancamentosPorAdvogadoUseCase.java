package br.com.exemplo.crudadvogado.core.application.usecase.lancamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LancamentoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.lancamento.LancamentoComParcelasResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.lancamento.ParcelaResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuscarLancamentosPorAdvogadoUseCase {

    private final LancamentoGateway lancamentoGateway;
    private final ProcessoGateway processoGateway;

    public BuscarLancamentosPorAdvogadoUseCase(
            LancamentoGateway lancamentoGateway,
            ProcessoGateway processoGateway) {
        this.lancamentoGateway = lancamentoGateway;
        this.processoGateway = processoGateway;
    }

    public List<LancamentoComParcelasResponse> executar(UUID idAdvogado) {
        var processosDoAdvogado = processoGateway.buscarPorAdvogado(idAdvogado);

        if (processosDoAdvogado.isEmpty()) {
            return List.of();
        }

        // 2. Extrair IDs dos processos
        var idsProcessos = processosDoAdvogado.stream()
                .map(processo -> processo.getIdProcesso())
                .collect(Collectors.toList());

        // 3. Buscar lançamentos relacionados a esses processos
        var lancamentos = lancamentoGateway.buscarPorProcessos(idsProcessos);

        // 4. Para cada lançamento, buscar suas parcelas e montar o response
        return lancamentos.stream()
                .map(lancamento -> {
                    var parcelas = lancamentoGateway.buscarParcelasPorLancamento(lancamento.getIdLancamento());

                    var parcelasResponse = parcelas.stream()
                            .map(parcela -> new ParcelaResponse(
                                    parcela.getIdParcela(),
                                    parcela.getIdLancamento(),
                                    parcela.getValor(),
                                    parcela.getDataVencimento(),
                                    parcela.getStatus()
                            ))
                            .collect(Collectors.toList());

                    return new LancamentoComParcelasResponse(
                            lancamento.getIdLancamento(),
                            lancamento.getIdCliente(),
                            lancamento.getIdProcesso(),
                            lancamento.getTitulo(),
                            parcelasResponse
                    );
                })
                .collect(Collectors.toList());
    }
}

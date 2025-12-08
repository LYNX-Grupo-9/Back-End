package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Lancamento;
import br.com.exemplo.crudadvogado.core.domain.Parcela;

import java.util.List;
import java.util.UUID;

public interface LancamentoGateway {
    Lancamento criar(Lancamento domain);
    List<Lancamento> buscarPorProcessos(List<UUID> idsProcessos);
    List<Parcela> buscarParcelasPorLancamento(Long idLancamento);
}

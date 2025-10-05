package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Anexo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnexoGateway {
    Anexo criar(Anexo domain);
    List<Anexo> buscarPorIdCliente(UUID idCliente);
    List<Anexo> buscarPorIdProcesso(UUID idProcesso);
    boolean existePorIdItem(String idItem);
    void deletarPorIdItem(String idItem);
    List<Anexo> buscarPorId(Long idAnexo);
}
package br.com.exemplo.crudadvogado.core.adapter.gateway;

import br.com.exemplo.crudadvogado.core.domain.Processo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ProcessoGateway {
    Processo criar(Processo domain);
    Long contarProcessosPorCliente(UUID idCliente);
    List<Processo> listarPorIdAdvogado(UUID idAdvogado);
    Optional<Processo> buscarPorId(UUID idProcesso);
    List<Processo> listarPorIdCliente(UUID idCliente);
    void excluirPorId(UUID idProcesso);
    boolean existePorId(UUID idProcesso);
    List<Processo> listarProcessosAtivosPorAdvogado(UUID idAdvogado);
    Map<String, Long> contarProcessosPorStatusPorAdvogado(UUID idAdvogado);
    List<Processo> buscarPorNumeroTituloOuCliente(String termo, UUID idAdvogado);
    List<Processo> listarProcessosOrdenadosPorStatus(UUID idAdvogado);
    Double calcularValorMedioPorAdvogado(UUID idAdvogado);
    List<Processo> listarProcessosOrdenadosPorValor(UUID idAdvogado);
    Map<String, Long> contarProcessosPorClasseProcessualPorAdvogado(UUID idAdvogado);
    Optional<Processo> buscarPorNumeroProcesso(String numeroProcesso);
    List<Processo> listarProcessosOrdenadosPorNomeCliente(UUID idAdvogado);
    List<Processo> listarProcessosOrdenadosPorNumeroProcesso(UUID idAdvogado);
    Processo atualizar(Processo domain);
}

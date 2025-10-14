package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.processo.AtualizarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ProcessoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Processo;

import java.util.UUID;

public class AtualizarProcessoParcialmenteUseCase {
    private final ProcessoGateway processoGateway;

    public AtualizarProcessoParcialmenteUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public ProcessoResponse executar(UUID id, AtualizarProcessoCommand request) {
        Processo processo = processoGateway.buscarPorId(id)
                .orElseThrow(() -> new ProcessoNaoEncontradoException("Processo não encontrado"));

        atualizarCampos(processo, request);

        Processo processoAtualizado = processoGateway.criar(processo);
        return new ProcessoResponse(processoAtualizado);
    }

    private void atualizarCampos(Processo processo, AtualizarProcessoCommand request) {
        if (request.titulo() != null) {
            processo.setTitulo(request.titulo());
        }
        if (request.numeroProcesso() != null) {
            processo.setNumeroProcesso(request.numeroProcesso());
        }
        if (request.descricao() != null) {
            processo.setDescricao(request.descricao());
        }
        if (request.status() != null) {
            processo.setStatus(request.status());
        }
        if (request.classeProcessual() != null) {
            processo.setClasseProcessual(request.classeProcessual());
        }
        if (request.assunto() != null) {
            processo.setAssunto(request.assunto());
        }
        if (request.tribunal() != null) {
            processo.setTribunal(request.tribunal());
        }
        if (request.valor() != null) {
            processo.setValor(request.valor());
        }
        if (request.autor() != null) {
            processo.setAutor(request.autor());
        }
        if (request.advRequerente() != null) {
            processo.setAdvRequerente(request.advRequerente());
        }
        if (request.reu() != null) {
            processo.setReu(request.reu());
        }
        if (request.advReu() != null) {
            processo.setAdvReu(request.advReu());
        }
        if (request.idAdvogado() != null) {
            processo.setIdAdvogado(request.idAdvogado());
        }
        if (request.idCliente() != null) {
            processo.setIdCliente(request.idCliente());
        }
    }
}

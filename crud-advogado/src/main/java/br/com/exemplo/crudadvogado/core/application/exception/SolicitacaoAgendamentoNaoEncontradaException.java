package br.com.exemplo.crudadvogado.core.application.exception;

public class SolicitacaoAgendamentoNaoEncontradaException extends RuntimeException {
    public SolicitacaoAgendamentoNaoEncontradaException(String message) {
        super(message);
    }
}
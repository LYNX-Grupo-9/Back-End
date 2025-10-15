package br.com.exemplo.crudadvogado.core.application.exception;

public class EventoNaoEncontradoException extends RuntimeException {
    public EventoNaoEncontradoException(String message) {
        super(message);
    }
}
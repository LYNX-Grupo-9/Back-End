package br.com.exemplo.crudadvogado.core.application.exception;

public class ParcelaNaoEncontradaException extends RuntimeException {

    public ParcelaNaoEncontradaException(String message) {
        super(message);
    }

    public ParcelaNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
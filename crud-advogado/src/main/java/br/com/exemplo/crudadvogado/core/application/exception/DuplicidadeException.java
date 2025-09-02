package br.com.exemplo.crudadvogado.core.application.exception;

public class DuplicidadeException extends RuntimeException {
    public DuplicidadeException(String message) {
        super(message);
    }
}

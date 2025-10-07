package br.com.exemplo.crudadvogado.core.application.exception;

public class CategoriaEventoNotFoundException extends RuntimeException {
    public CategoriaEventoNotFoundException(String message) {
        super(message);
    }
}
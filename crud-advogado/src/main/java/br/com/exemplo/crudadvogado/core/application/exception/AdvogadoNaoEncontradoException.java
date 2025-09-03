package br.com.exemplo.crudadvogado.core.application.exception;

public class AdvogadoNaoEncontradoException extends RuntimeException {
  public AdvogadoNaoEncontradoException(String message) {
    super(message);
  }
}

package br.com.exemplo.crudadvogado.core.application.exception;

public class ProcessoNaoEncontradoException extends RuntimeException {
  public ProcessoNaoEncontradoException(String message) {
    super(message);
  }
}

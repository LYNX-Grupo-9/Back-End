package br.com.exemplo.crudadvogado.core.domain;

import java.util.UUID;

public class Lancamento {

    Long idLancamento;
    String titulo;
    UUID idProcesso;
    UUID idCliente;

    public Lancamento(String titulo, UUID idProcesso, UUID idCliente) {
        this.titulo = titulo;
        this.idProcesso = idProcesso;
        this.idCliente = idCliente;
    }

    public Long getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UUID getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(UUID idProcesso) {
        this.idProcesso = idProcesso;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public static Lancamento criarNovo(String titulo, UUID idProcesso, UUID idCliente) {
        return new Lancamento(titulo, idProcesso, idCliente);
    }
}

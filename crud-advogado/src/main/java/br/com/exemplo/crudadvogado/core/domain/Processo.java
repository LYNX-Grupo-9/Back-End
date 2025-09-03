package br.com.exemplo.crudadvogado.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class Processo {

    private String titulo;
    private String numeroProcesso;
    private String descricao;
    private String status;
    private String classeProcessual;
    private String assunto;
    private String tribunal;
    private BigDecimal valor;
    private String autor;
    private String advRequerente;
    private String reu;
    private String advReu;

    private Advogado advogado;
    private Cliente cliente;
    private List<Evento> eventos;
    private Anexo anexo;


    public Processo(String titulo, String numeroProcesso, String descricao, String status, String classeProcessual, String assunto, String tribunal, BigDecimal valor, String autor, String advRequerente, String reu, String advReu) {
        this.titulo = titulo;
        this.numeroProcesso = numeroProcesso;
        this.descricao = descricao;
        this.status = status;
        this.classeProcessual = classeProcessual;
        this.assunto = assunto;
        this.tribunal = tribunal;
        this.valor = valor;
        this.autor = autor;
        this.advRequerente = advRequerente;
        this.reu = reu;
        this.advReu = advReu;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClasseProcessual() {
        return classeProcessual;
    }

    public void setClasseProcessual(String classeProcessual) {
        this.classeProcessual = classeProcessual;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTribunal() {
        return tribunal;
    }

    public void setTribunal(String tribunal) {
        this.tribunal = tribunal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAdvRequerente() {
        return advRequerente;
    }

    public void setAdvRequerente(String advRequerente) {
        this.advRequerente = advRequerente;
    }

    public String getReu() {
        return reu;
    }

    public void setReu(String reu) {
        this.reu = reu;
    }

    public String getAdvReu() {
        return advReu;
    }

    public void setAdvReu(String advReu) {
        this.advReu = advReu;
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }
}

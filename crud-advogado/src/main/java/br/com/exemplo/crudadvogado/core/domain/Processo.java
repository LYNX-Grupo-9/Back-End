package br.com.exemplo.crudadvogado.core.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Processo {

    private UUID idProcesso;
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

    private UUID idAdvogado;
    private UUID idCliente;
    private List<UUID> eventos;
//    private Long anexo;


    public Processo(UUID idProcesso, String titulo, String numeroProcesso, String descricao, String status, String classeProcessual, String assunto, String tribunal, BigDecimal valor, String autor, String advRequerente, String reu, String advReu, UUID idAdvogado, UUID idCliente, List<UUID> eventos) {
        this.idProcesso = idProcesso;
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
        this.idAdvogado = idAdvogado;
        this.idCliente = idCliente;
        this.eventos = eventos;
    }

    public UUID getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(UUID idProcesso) {
        this.idProcesso = idProcesso;
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

    public UUID getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(UUID idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public List<UUID> getEventos() {
        return eventos;
    }

    public void setEventos(List<UUID> eventos) {
        this.eventos = eventos;
    }

    public static Processo criarNovo(String titulo, String numeroProcesso, String descricao, String status, String classeProcessual, String assunto, String tribunal, BigDecimal valor, String autor, String advRequerente, String reu, String advReu, UUID advogado, UUID cliente) {
        return new Processo(
                UUID.randomUUID(),
                titulo,
                numeroProcesso,
                descricao,
                status,
                classeProcessual,
                assunto,
                tribunal,
                valor,
                autor,
                advRequerente,
                reu,
                advReu,
                advogado,
                cliente,
                List.of()
        );
    }
}

package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "processo")
public class ProcessoEntity {

    @Id
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

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "id_advogado")
    private AdvogadoEntity advogado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "processo")
    private List<EventoEntity> eventos;

//    @ManyToOne
//    @JoinColumn(name = "id_anexo")
//    private Anexo anexo;


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

    public AdvogadoEntity getAdvogado() {
        return advogado;
    }

    public void setAdvogado(AdvogadoEntity advogado) {
        this.advogado = advogado;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }
}

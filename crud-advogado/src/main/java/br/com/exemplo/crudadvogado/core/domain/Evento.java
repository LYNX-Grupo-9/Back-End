package br.com.exemplo.crudadvogado.core.domain;

import java.util.Date;
import java.time.LocalTime;
import java.util.UUID;

public class Evento {

    private Long idEvento;
    private String nome;
    private String descricao;
    private String local;
    private String linkReuniao;
    private Date dataReuniao;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    private UUID advogado;
    private UUID cliente;
    private Long categoria;
//    private Long processo;

    public Evento(Long idEvento, String nome, String descricao, String local, String linkReuniao, Date dataReuniao, LocalTime horaInicio, LocalTime horaFim, UUID advogado, UUID cliente, Long categoria) {
        this.idEvento = idEvento;
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.linkReuniao = linkReuniao;
        this.dataReuniao = dataReuniao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.advogado = advogado;
        this.cliente = cliente;
        this.categoria = categoria;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLinkReuniao() {
        return linkReuniao;
    }

    public void setLinkReuniao(String linkReuniao) {
        this.linkReuniao = linkReuniao;
    }

    public Date getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(Date dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public UUID getAdvogado() {
        return advogado;
    }

    public void setAdvogado(UUID advogado) {
        this.advogado = advogado;
    }

    public UUID getCliente() {
        return cliente;
    }

    public void setCliente(UUID cliente) {
        this.cliente = cliente;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    public static Evento criarNovo(String nome, String descricao, String local, String linkReuniao, Date dataReuniao, LocalTime horaInicio, LocalTime horaFim, UUID advogado, UUID cliente, Long categoria) {
        return new Evento(null, nome, descricao, local, linkReuniao, dataReuniao, horaInicio, horaFim, advogado, cliente, categoria);
    }
}

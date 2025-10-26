package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "evento")
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idEvento;

    private String nome;
    private String descricao;
    private String local;
    private String linkReuniao;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataReuniao;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaInicio;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaFim;


    @ManyToOne
    @JoinColumn(name = "id_advogado")
    private AdvogadoEntity advogado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaEventoEntity categoria;

    @ManyToOne
    @JoinColumn(name = "id_processo")
    private ProcessoEntity processo;


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

    public CategoriaEventoEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEventoEntity categoria) {
        this.categoria = categoria;
    }

    public ProcessoEntity getProcesso() {
        return processo;
    }

    public void setProcesso(ProcessoEntity processo) {
        this.processo = processo;
    }
}

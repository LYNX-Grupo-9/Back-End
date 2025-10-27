package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lancamento")
public class LancamentoEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idLancamento;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "idProcesso")
    private ProcessoEntity processo;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClienteEntity cliente;

    public LancamentoEntity() {
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

    public ProcessoEntity getProcesso() {
        return processo;
    }

    public void setProcesso(ProcessoEntity processo) {
        this.processo = processo;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}

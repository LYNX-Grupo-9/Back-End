package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.time.LocalTime;

@Entity
@Table(name = "solicitacao_agendamento")
public class SolicitacaoAgendamentoEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idSolicitacaoAgendamento;

    private String nome;
    private String telefone;
    private String email;
    private String assunto;
    private Date dataSolicitacao;
    private LocalTime horaSolicitacao;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean visualizado = false;

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "idAdvogado")
    private AdvogadoEntity advogado;

    public Long getIdSolicitacaoAgendamento() {
        return idSolicitacaoAgendamento;
    }

    public void setIdSolicitacaoAgendamento(Long idSolicitacaoAgendamento) {
        this.idSolicitacaoAgendamento = idSolicitacaoAgendamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalTime getHoraSolicitacao() {
        return horaSolicitacao;
    }

    public void setHoraSolicitacao(LocalTime horaSolicitacao) {
        this.horaSolicitacao = horaSolicitacao;
    }

    public Boolean getVisualizado() {
        return visualizado;
    }

    public void setVisualizado(Boolean visualizado) {
        this.visualizado = visualizado;
    }

    public AdvogadoEntity getAdvogado() {
        return advogado;
    }

    public void setAdvogado(AdvogadoEntity advogado) {
        this.advogado = advogado;
    }
}

package br.com.exemplo.crudadvogado.core.domain;

import java.util.Date;
import java.time.LocalTime;
import java.util.UUID;

public class SolicitacaoAgendamento {

    private Long idSolicitacaoAgendamento;
    private String nome;
    private String telefone;
    private String email;
    private String assunto;
    private Date dataSolicitacao;
    private LocalTime horaSolicitacao;
    private Boolean visualizado;

    // RELACIONAMENTOS
    private UUID idAdvogado;

    public SolicitacaoAgendamento(Long idSolicitacaoAgendamento, String nome, String telefone, String email, String assunto, Date dataSolicitacao, LocalTime horaSolicitacao, Boolean visualizado, UUID idAdvogado) {
        this.idSolicitacaoAgendamento = idSolicitacaoAgendamento;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.assunto = assunto;
        this.dataSolicitacao = dataSolicitacao;
        this.horaSolicitacao = horaSolicitacao;
        this.visualizado = visualizado;
        this.idAdvogado = idAdvogado;
    }

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

    public UUID getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(UUID idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public static SolicitacaoAgendamento criarNovo(String nome, String telefone, String email, String assunto, Date dataSolicitacao, LocalTime horaSolicitacao, UUID advogado) {
        return new SolicitacaoAgendamento(null, nome, telefone, email, assunto, dataSolicitacao, horaSolicitacao, false, advogado);
    }
}

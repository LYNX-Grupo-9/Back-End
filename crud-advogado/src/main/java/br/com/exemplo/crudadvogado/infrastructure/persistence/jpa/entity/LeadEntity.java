package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lead")
public class LeadEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idLead;

    private String nome;
    private String telefone;
    private String email;
    private String assunto;
    private String mensagem;

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "id_advogado")
    private AdvogadoEntity advogado;

    public Long getIdLead() {
        return idLead;
    }

    public void setIdLead(Long idLead) {
        this.idLead = idLead;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public AdvogadoEntity getAdvogado() {
        return advogado;
    }

    public void setAdvogado(AdvogadoEntity advogado) {
        this.advogado = advogado;
    }
}

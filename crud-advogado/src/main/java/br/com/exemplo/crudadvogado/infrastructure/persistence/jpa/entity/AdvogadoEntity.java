package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "advogado")
public class AdvogadoEntity {

    @Id
    private UUID idAdvogado;
    private String nome;
    @JsonProperty("oab")
    private String registroOab;
    private String cpf;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "advogado")
    private List<ClienteEntity> clientes;

    @OneToMany(mappedBy = "advogado")
    private List<ProcessoEntity> processos;

    @OneToMany(mappedBy = "advogado")
    private List<EventoEntity> eventos;

//    @OneToMany(mappedBy = "advogado")
//    private List<Lead> leads;

    @OneToMany(mappedBy = "advogado")
    private List<SolicitacaoAgendamentoEntity> solicitacoesAgendamento;

    @OneToMany(mappedBy = "advogado")
    private List<CategoriaEventoEntity> categoriasEvento;

    public AdvogadoEntity() {
    }

    public AdvogadoEntity(String nome, String oab, String cpf, String email, String senha) {
        this.idAdvogado = UUID.randomUUID();
        this.nome = nome;
        this.registroOab = oab;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public UUID getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(UUID idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistroOab() {
        return registroOab;
    }

    public void setRegistroOab(String registroOab) {
        this.registroOab = registroOab;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }

    public List<ProcessoEntity> getProcessos() {
        return processos;
    }

    public void setProcessos(List<ProcessoEntity> processos) {
        this.processos = processos;
    }

    public List<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }

    public List<SolicitacaoAgendamentoEntity> getSolicitacoesAgendamento() {
        return solicitacoesAgendamento;
    }

    public void setSolicitacoesAgendamento(List<SolicitacaoAgendamentoEntity> solicitacoesAgendamento) {
        this.solicitacoesAgendamento = solicitacoesAgendamento;
    }

    public List<CategoriaEventoEntity> getCategoriasEvento() {
        return categoriasEvento;
    }

    public void setCategoriasEvento(List<CategoriaEventoEntity> categoriasEvento) {
        this.categoriasEvento = categoriasEvento;
    }
}

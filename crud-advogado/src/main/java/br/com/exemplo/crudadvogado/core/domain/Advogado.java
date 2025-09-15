package br.com.exemplo.crudadvogado.core.domain;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.advogado.Oab;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.advogado.Senha;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Cpf;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Advogado {

    private UUID idAdvogado;
    private String nome;
    private Oab oab;
    private Cpf cpf;
    private Email email;
    private Senha senha;

    private List<UUID> clientes;
    private List<UUID> processos;
    private List<UUID> eventos;
    private List<UUID> leads;
    private List<UUID> solicitacoesAgendamento;
    private List<UUID> categoriasEvento;

    private Advogado(UUID idAdvogado, String nome, Oab oab, Cpf cpf, Email email, Senha senha) {
        this.idAdvogado = idAdvogado;
        this.nome = nome;
        this.oab = oab;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.clientes = new ArrayList<>();
        this.processos = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.leads = new ArrayList<>();
        this.solicitacoesAgendamento = new ArrayList<>();
        this.categoriasEvento = new ArrayList<>();
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

    public Oab getOab() {
        return oab;
    }

    public void setOab(Oab oab) {
        this.oab = oab;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Senha getSenha() {
        return senha;
    }

    public void setSenha(Senha senha) {
        this.senha = senha;
    }

    public List<UUID> getClientes() {
        return clientes;
    }

    public void setClientes(List<UUID> clientes) {
        this.clientes = clientes;
    }

    public List<UUID> getProcessos() {
        return processos;
    }

    public void setProcessos(List<UUID> processos) {
        this.processos = processos;
    }

    public List<UUID> getEventos() {
        return eventos;
    }

    public void setEventos(List<UUID> eventos) {
        this.eventos = eventos;
    }

    public List<UUID> getLeads() {
        return leads;
    }

    public void setLeads(List<UUID> leads) {
        this.leads = leads;
    }

    public List<UUID> getSolicitacoesAgendamento() {
        return solicitacoesAgendamento;
    }

    public void setSolicitacoesAgendamento(List<UUID> solicitacoesAgendamento) {
        this.solicitacoesAgendamento = solicitacoesAgendamento;
    }

    public List<UUID> getCategoriasEvento() {
        return categoriasEvento;
    }

    public void setCategoriasEvento(List<UUID> categoriasEvento) {
        this.categoriasEvento = categoriasEvento;
    }

    public static Advogado criarNovo(String nome, String oab, String cpf, String email, String senha) {
        String uniqueKey = oab + cpf + email;
        UUID id = UUID.nameUUIDFromBytes(uniqueKey.getBytes());
        return new Advogado(id, nome, Oab.criar(oab), Cpf.criar(cpf), Email.criar(email), Senha.criar(senha));
    }
}

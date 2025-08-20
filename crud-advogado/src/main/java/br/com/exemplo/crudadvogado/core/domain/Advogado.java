package br.com.exemplo.crudadvogado.core.domain;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.Cpf;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.Oab;

import java.util.List;
import java.util.UUID;

public class Advogado {

    private UUID idAdvogado;
    private String nome;
    private Oab registroOab;
    private Cpf cpf;
    private Email email;
    private String senha;

    private List<Cliente> clientes;
    private List<Processo> processos;
    private List<Evento> eventos;
    private List<Lead> leads;
    private List<SolicitacaoAgendamento> solicitacoesAgendamento;
    private List<CategoriaEvento> categoriasEvento;

    public Advogado(UUID idAdvogado, String nome, Oab registroOab, Cpf cpf, Email email, String senha) {
        this.idAdvogado = idAdvogado;
        this.nome = nome;
        this.registroOab = registroOab;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Advogado() {

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

    public Oab getRegistroOab() {
        return registroOab;
    }

    public void setRegistroOab(Oab registroOab) {
        this.registroOab = registroOab;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Advogado criar(String nome, Oab registroOab, Cpf cpf, Email email, String senha) throws IllegalArgumentException {

        Email.criar(email.getEndereco());
        Cpf.criar(cpf.getNumero());
        Oab.criar(registroOab.getNumero());

        Advogado advogado = new Advogado();
        advogado.setNome(nome);
        advogado.setRegistroOab(registroOab);
        advogado.setCpf(cpf);
        advogado.setEmail(email);
        advogado.setSenha(senha);

        return advogado;
    }
}

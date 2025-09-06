package br.com.exemplo.crudadvogado.core.domain;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.advogado.Oab;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.advogado.Senha;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Cpf;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;

import java.util.List;
import java.util.UUID;

public class Advogado {

    private UUID idAdvogado;
    private String nome;
    private Oab oab;
    private Cpf cpf;
    private Email email;
    private Senha senha;

    private Advogado(UUID idAdvogado, String nome, Oab oab, Cpf cpf, Email email, Senha senha) {
        this.idAdvogado = idAdvogado;
        this.nome = nome;
        this.oab = oab;
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

    public static Advogado criarNovo(String nome, String oab, String cpf, String email, String senha) {
        String uniqueKey = oab + cpf + email;
        UUID id = UUID.nameUUIDFromBytes(uniqueKey.getBytes());
        return new Advogado(id, nome, Oab.criar(oab), Cpf.criar(cpf), Email.criar(email), Senha.criar(senha));
    }
}

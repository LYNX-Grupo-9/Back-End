package br.com.exemplo.crudadvogado.core.dto.advogado;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.Cpf;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.Oab;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;

public class NovoAdvogadoDTO {

    private String nome;
    private Oab registroOab;
    private Cpf cpf;
    private Email email;
    private String senha;

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
}

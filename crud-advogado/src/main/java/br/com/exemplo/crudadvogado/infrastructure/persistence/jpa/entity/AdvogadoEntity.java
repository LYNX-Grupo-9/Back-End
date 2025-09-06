package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
}

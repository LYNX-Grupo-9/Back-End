package br.com.exemplo.crudadvogado.core.domain;

import java.util.List;
import java.util.UUID;

public class CategoriaEvento {

    private Long idCategoria;
    private String nome;
    private String cor;

    private UUID idAdvogado;
    private List<UUID> eventos;

    public CategoriaEvento(String nome, String cor, UUID idAdvogado) {
        this.nome = nome;
        this.cor = cor;
        this.idAdvogado = idAdvogado;
    }

    public CategoriaEvento(Long idCategoria, String nome, String cor, UUID idAdvogado) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.cor = cor;
        this.idAdvogado = idAdvogado;
    }


    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public UUID getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(UUID idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public List<UUID> getEventos() {
        return eventos;
    }

    public void setEventos(List<UUID> eventos) {
        this.eventos = eventos;
    }

    public static CategoriaEvento criarNovo(String nome, String cor, UUID idAdvogado) {
        return new CategoriaEvento(nome, cor, idAdvogado);
    }

    public static CategoriaEvento criarExistente(Long idCategoria, String nome, String cor, UUID idAdvogado) {
        return new CategoriaEvento(idCategoria, nome, cor, idAdvogado);
    }
}

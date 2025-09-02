package br.com.exemplo.crudadvogado.core.domain;

public class CategoriaEvento {

    private String nome;
    private String cor;

//    private Advogado advogado;
//    private List<Evento> eventos;

    public CategoriaEvento(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
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
}

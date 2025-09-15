package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categoria_evento")
public class CategoriaEventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cor;

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "id_advogado")
    private AdvogadoEntity advogado;

    @OneToMany(mappedBy = "categoria")
    private List<EventoEntity> eventos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AdvogadoEntity getAdvogado() {
        return advogado;
    }

    public void setAdvogado(AdvogadoEntity advogado) {
        this.advogado = advogado;
    }

    public List<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }
}

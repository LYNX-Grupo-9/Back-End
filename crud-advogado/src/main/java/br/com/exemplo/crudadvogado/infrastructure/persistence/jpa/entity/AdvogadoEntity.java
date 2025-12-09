package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
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

    public void setId(long l) {
    }
}

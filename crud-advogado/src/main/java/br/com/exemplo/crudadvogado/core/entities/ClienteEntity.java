package br.com.exemplo.crudadvogado.core.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idCliente;

    private String nome;
    private String documento;
    private String tipoDocumento;
    private String email;
    private String telefone;
    private String endereco;
    private String genero;
    private Date dataNascimento;
    private String estadoCivil;
    private String profissao;
    private String passaporte;
    private String cnh;
    private String naturalidade;
    private Long qtdProcessos;

//    @ManyToOne
//    @JoinColumn(name = "id_advogado")
//    private AdvogadoEntity advogado;
//
//    @OneToMany(mappedBy = "cliente")
//    private List<ProcessoEntity> processos;
//
//    @OneToMany(mappedBy = "cliente")
//    private List<AnexoEntity> anexos;
//
//    @OneToMany(mappedBy = "cliente")
//    private List<EventoEntity> eventos;

}

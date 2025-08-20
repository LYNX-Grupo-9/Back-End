package br.com.exemplo.crudadvogado.core.entities;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.Cpf;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.Oab;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "advogado")
public class AdvogadoEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private UUID idAdvogado;

    private String nome;
    private String registroOab;
    private String cpf;
    private String email;
    private String senha;

//    // RELACIONAMENTOS
//    @OneToMany(mappedBy = "advogado")
//    private List<Cliente> clientes;
//
//    @OneToMany(mappedBy = "advogado")
//    private List<Processo> processos;
//
//    @OneToMany(mappedBy = "advogado")
//    private List<Evento> eventos;
//
//    @OneToMany(mappedBy = "advogado")
//    private List<Lead> leads;
//
//    @OneToMany(mappedBy = "advogado")
//    private List<SolicitacaoAgendamento> solicitacoesAgendamento;
//
//    @OneToMany(mappedBy = "advogado")
//    private List<CategoriaEvento> categoriasEvento;

}

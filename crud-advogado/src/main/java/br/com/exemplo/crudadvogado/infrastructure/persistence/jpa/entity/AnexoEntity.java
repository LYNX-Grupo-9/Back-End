package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "anexo")
public class AnexoEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idAnexo;
    private String nomeAnexo;
    private String idItem;
    
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClienteEntity cliente;
    
    @ManyToOne
    @JoinColumn(name = "idProcesso")
    private ProcessoEntity processo;


    public AnexoEntity(Long idAnexo, String nomeAnexo, UUID idCliente, UUID idProcesso) {
    }

    public AnexoEntity() {

    }

    public Long getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Long idAnexo) {
        this.idAnexo = idAnexo;
    }

    public String getNomeAnexo() {
        return nomeAnexo;
    }

    public void setNomeAnexo(String nomeAnexo) {
        this.nomeAnexo = nomeAnexo;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public ProcessoEntity getProcesso() {
        return processo;
    }

    public void setProcesso(ProcessoEntity processo) {
        this.processo = processo;
    }
}

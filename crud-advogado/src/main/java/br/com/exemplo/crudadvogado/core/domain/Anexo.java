package br.com.exemplo.crudadvogado.core.domain;

import java.util.UUID;

public class Anexo {

    private Long idAnexo;
    private String nomeAnexo;
    private String idItem; //Id do item no bucket

     private UUID idCliente;
     private UUID idProcesso;

    private Anexo(String nomeAnexo, String idItem, UUID idCliente, UUID idProcesso) {
        this.nomeAnexo = nomeAnexo;
        this.idItem = idItem;
        this.idCliente = idCliente;
        this.idProcesso = idProcesso;
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

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public UUID getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(UUID idProcesso) {
        this.idProcesso = idProcesso;
    }

    public static Anexo criarNovo(String nomeAnexo, String idItem, UUID idCliente, UUID idProcesso) {
        return new Anexo(nomeAnexo, idItem, idCliente, idProcesso);
    }
}

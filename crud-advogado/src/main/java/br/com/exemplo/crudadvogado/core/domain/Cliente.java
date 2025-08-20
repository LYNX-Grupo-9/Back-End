package br.com.exemplo.crudadvogado.core.domain;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;

import java.sql.Date;

public class Cliente {
    private Integer idCliente;
    private String nome;
    private String documento;
    private String tipoDocumento;
    private Email email;
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public Email getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getGenero() {
        return genero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getProfissao() {
        return profissao;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public String getCnh() {
        return cnh;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public Long getQtdProcessos() {
        return qtdProcessos;
    }
}

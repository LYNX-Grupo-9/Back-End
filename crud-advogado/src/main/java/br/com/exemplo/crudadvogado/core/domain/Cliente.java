package br.com.exemplo.crudadvogado.core.domain;



import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Cliente {

    private UUID idCliente;
    private String nome;
    private String documento;
    private String tipoDocumento;
    private Email email;
    private String telefone;
    private String endereco;
    private Genero genero;
    private Date dataNascimento;
    private EstadoCivil estadoCivil;
    private String profissao;
    private String passaporte;
    private String cnh;
    private String naturalidade;
    private Long qtdProcessos;

    private UUID idAdvogado;
    private List<UUID> processos;
    private List<UUID> anexos;
    private List<Long> eventos;

    public Cliente(UUID idCliente, String nome, String documento, String tipoDocumento, Email email, String telefone, String endereco, Genero genero, Date dataNascimento, EstadoCivil estadoCivil, String profissao, String passaporte, String cnh, String naturalidade, Long qtdProcessos, UUID idAdvogado) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.estadoCivil = estadoCivil;
        this.profissao = profissao;
        this.passaporte = passaporte;
        this.cnh = cnh;
        this.naturalidade = naturalidade;
        this.qtdProcessos = qtdProcessos;
        this.idAdvogado = idAdvogado;
        this.processos = new ArrayList<>();
        this.anexos = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }

    public Cliente(UUID idCliente, String nome, String documento, String tipoDocumento,
                   Email email, String telefone, String endereco, Genero genero,
                   Date dataNascimento, EstadoCivil estadoCivil, String profissao,
                   String passaporte, String cnh, String naturalidade, Long qtdProcessos,
                   UUID idAdvogado, List<UUID> processos, List<UUID> anexos, List<Long> eventos) {

        this.idCliente = idCliente;
        this.nome = nome;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.estadoCivil = estadoCivil;
        this.profissao = profissao;
        this.passaporte = passaporte;
        this.cnh = cnh;
        this.naturalidade = naturalidade;
        this.qtdProcessos = qtdProcessos;
        this.idAdvogado = idAdvogado;

        this.processos = processos != null ? processos : new ArrayList<>();
        this.anexos = anexos != null ? anexos : new ArrayList<>();
        this.eventos = eventos != null ? eventos : new ArrayList<>();
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Long getQtdProcessos() {
        return qtdProcessos;
    }

    public void setQtdProcessos(Long qtdProcessos) {
        this.qtdProcessos = qtdProcessos;
    }

    public UUID getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(UUID idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public List<UUID> getProcessos() {
        return processos;
    }

    public void setProcessos(List<UUID> processos) {
        this.processos = processos;
    }

    public List<UUID> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<UUID> anexos) {
        this.anexos = anexos;
    }

    public List<Long> getEventos() {
        return eventos;
    }

    public void setEventos(List<Long> eventos) {
        this.eventos = eventos;
    }

    public static Cliente criarNovo(String nome, String documento, String tipoDocumento, String email, String telefone, String endereco, String genero, Date dataNascimento, String estadoCivil, String profissao, String passaporte, String cnh, String naturalidade, UUID idAdvogado) {
        String uniqueKey = documento + email + telefone;
        UUID id = UUID.nameUUIDFromBytes(uniqueKey.getBytes());
        return new Cliente(id, nome, documento, tipoDocumento, Email.criar(email), telefone, endereco, Genero.criar(genero), dataNascimento, EstadoCivil.criar(estadoCivil), profissao, passaporte, cnh, naturalidade, 0L, idAdvogado);
    }
}

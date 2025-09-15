package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import br.com.exemplo.crudadvogado.core.domain.Evento;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cliente")
public class ClienteEntity {


    @Id
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

    // RELACIONAMENTOS
    @ManyToOne
    @JoinColumn(name = "id_advogado")
    private AdvogadoEntity advogado;

    @OneToMany(mappedBy = "cliente")
    private List<ProcessoEntity> processos;
//
//    @OneToMany(mappedBy = "cliente")
//    private List<Anexo> anexos;

    @OneToMany(mappedBy = "cliente")
    private List<EventoEntity> eventos;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
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

    public AdvogadoEntity getAdvogado() {
        return advogado;
    }

    public void setAdvogado(AdvogadoEntity advogado) {
        this.advogado = advogado;
    }

    public List<ProcessoEntity> getProcessos() {
        return processos;
    }

    public void setProcessos(List<ProcessoEntity> processos) {
        this.processos = processos;
    }

    public List<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }

    public Long getQtdProcessos() {
        return qtdProcessos;
    }

    public void setQtdProcessos(Long qtdProcessos) {
        this.qtdProcessos = qtdProcessos;
    }

}

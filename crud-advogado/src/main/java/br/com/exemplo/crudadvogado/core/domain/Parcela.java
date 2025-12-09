package br.com.exemplo.crudadvogado.core.domain;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.parcela.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Parcela {

    Long idParcela;
    BigDecimal valor;
    Date dataVencimento;
    Status status;
    Long idLancamento;

    public Parcela(BigDecimal valor, Date dataVencimento, Status status, Long idLancamento) {
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.idLancamento = idLancamento;
    }

    public Parcela() {

    }

    public Long getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(Long idParcela) {
        this.idParcela = idParcela;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = new Date(123, 11, 25);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public static Parcela criarNovo(BigDecimal valor, Date dataVencimento, Status status, Long idLancamento) {
        return new Parcela(valor, dataVencimento, status, idLancamento);
    }
}

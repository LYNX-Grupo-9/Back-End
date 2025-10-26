package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "parcela")
public class ParcelaEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long idParcela;

    private BigDecimal valor;
    private Date dataVencimento;
    private String status;

    @ManyToOne()
    @JoinColumn(name = "id_lancamento")
    private LancamentoEntity lancamento;

    public ParcelaEntity() {
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

    public void setDataVencimento(Date vencimento) {
        this.dataVencimento = vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LancamentoEntity getLancamento() {
        return lancamento;
    }

    public void setLancamento(LancamentoEntity lancamento) {
        this.lancamento = lancamento;
    }

}

package br.com.exemplo.crudadvogado.core.domain;

import java.sql.Date;
import java.time.LocalTime;

public class SolicitacaoAgendamento {
    private String nome;
    private String telefone;
    private String email;
    private String assunto;
    private Date dataSolicitacao;
    private LocalTime horaSolicitacao;
    private Boolean visualizado;

    // RELACIONAMENTOS
    private Advogado advogado;

}

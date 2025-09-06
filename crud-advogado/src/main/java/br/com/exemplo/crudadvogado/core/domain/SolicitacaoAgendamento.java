package br.com.exemplo.crudadvogado.core.domain;

import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;

public class SolicitacaoAgendamento {

    private Long idSolicitacaoAgendamento;
    private String nome;
    private String telefone;
    private String email;
    private String assunto;
    private Date dataSolicitacao;
    private LocalTime horaSolicitacao;
    private Boolean visualizado;

    // RELACIONAMENTOS
    private UUID advogado;

}

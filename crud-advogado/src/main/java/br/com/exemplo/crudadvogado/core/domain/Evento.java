package br.com.exemplo.crudadvogado.core.domain;

import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;

public class Evento {

    private Long idEvento;
    private String nome;
    private String descricao;
    private String local;
    private String linkReuniao;
    private Date dataReuniao;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    private UUID advogado;
    private UUID cliente;
    private Long categoria;
    private Long processo;
}

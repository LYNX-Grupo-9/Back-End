package br.com.exemplo.crudadvogado.core.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Processo {

    private UUID idProcesso;
    private String titulo;
    private String numeroProcesso;
    private String descricao;
    private String status;
    private String classeProcessual;
    private String assunto;
    private String tribunal;
    private BigDecimal valor;
    private String autor;
    private String advRequerente;
    private String reu;
    private String advReu;

    private UUID advogado;
    private UUID cliente;
    private Long anexo;
}

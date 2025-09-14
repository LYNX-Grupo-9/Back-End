package br.com.exemplo.crudadvogado.core.domain;


import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;

import java.util.UUID;

public class Lead {

    private Long idLead;
    private String nome;
    private String telefone;
    private Email email;
    private String assunto;
    private String mensagem;

    // RELACIONAMENTOS
    private UUID advogado;
}

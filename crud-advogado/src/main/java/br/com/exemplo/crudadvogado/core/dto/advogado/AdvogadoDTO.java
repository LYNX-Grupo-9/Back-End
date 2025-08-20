package br.com.exemplo.crudadvogado.core.dto.advogado;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.Oab;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;

import java.util.UUID;

public class AdvogadoDTO {
    private UUID idAdvogado;
    private String nome;
    private Oab registroOab;
    private Email email;

    public AdvogadoDTO(UUID idAdvogado, String nome, Oab registroOab, Email email) {
        this.idAdvogado = idAdvogado;
        this.nome = nome;
        this.registroOab = registroOab;
        this.email = email;
    }

    public UUID getIdAdvogado() {
        return idAdvogado;
    }

    public String getNome() {
        return nome;
    }

    public Oab getRegistroOab() {
        return registroOab;
    }

    public Email getEmail() {
        return email;
    }
}

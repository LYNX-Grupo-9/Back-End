package br.com.exemplo.crudadvogado.core.application.dto.command;

public record CriarAdvogadoCommand (
        String nome,
        String email,
        String cpf,
        String oab,
        String senha
){}
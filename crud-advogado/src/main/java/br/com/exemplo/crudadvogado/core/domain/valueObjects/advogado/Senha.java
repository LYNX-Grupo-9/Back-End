package br.com.exemplo.crudadvogado.core.domain.valueObjects.advogado;

import java.util.regex.Pattern;

public class Senha {

    private String valor;

    private Senha(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private static final Pattern SENHA_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$");

    public static Boolean validarSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            return false;
        }
        return senha.matches(SENHA_PATTERN.pattern());
    }

    public static Senha criar(String senha) {
        if (validarSenha(senha)) {
            return new Senha(senha);
        }
        throw new IllegalArgumentException("Senha inválida. A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma letra minúscula, um número e um caractere especial.");
    }

}

package br.com.exemplo.crudadvogado.core.domain.valueObjects.shared;

import java.util.regex.Pattern;

public class Email {

    private String enderacoEmail;

    private Email(String enderacoEmail) {
        this.enderacoEmail = enderacoEmail;
    }

    public String getEnderacoEmail() {
        return enderacoEmail;
    }

    public void setEnderacoEmail(String enderacoEmail) {
        this.enderacoEmail = enderacoEmail;
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static Boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.matches(EMAIL_PATTERN.pattern());
    }

    public static Email criar(String email) {
        if (validarEmail(email)) {
            return new Email(email);
        }
        throw new IllegalArgumentException("Endereço de email inválido");
    }

}

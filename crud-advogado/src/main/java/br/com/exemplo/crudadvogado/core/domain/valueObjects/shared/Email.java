package br.com.exemplo.crudadvogado.core.domain.valueObjects.shared;

public class Email {
    private String endereco;

    public Email(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static Boolean validar(String endereco) {
        if (endereco == null || endereco.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        return endereco.matches("^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    }

    public static Email criar(String endereco) {
        if (validar(endereco)) {
            return new Email(endereco);
        }
        throw new IllegalArgumentException("Email inválido");
    }
}

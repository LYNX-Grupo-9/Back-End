package br.com.exemplo.crudadvogado.core.domain.valueObjects;

public class Cpf {
    private String numero;

    private Cpf(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public static Boolean validar(String numero) {
        if (numero == null || numero.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }
        return numero.matches("\\d{3}-\\d{3}-\\d{4}");
    }

    public static Cpf criar(String numero) {
        if (validar(numero)) {
            return new Cpf(numero);
        }
        throw new IllegalArgumentException("CPF inválido");
    }
}

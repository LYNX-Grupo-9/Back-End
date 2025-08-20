package br.com.exemplo.crudadvogado.core.domain.valueObjects;

public class Oab {
    private String numero;

    public Oab(String numero) {
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
            throw new IllegalArgumentException("Número da OAB não pode ser vazio");
        }
        return numero.matches("^[0-9]{2,3}\\.[0-9]{4,5}-[A-Z]{1,2}$");
    }

    public static Oab criar(String numero) {
        if (validar(numero)) {
            return new Oab(numero);
        }
        throw new IllegalArgumentException("Número da OAB inválido");
    }
}

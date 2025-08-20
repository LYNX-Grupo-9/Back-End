package br.com.exemplo.crudadvogado.core.usecase.advogado;

import br.com.exemplo.crudadvogado.core.domain.Advogado;
import br.com.exemplo.crudadvogado.core.gateway.AdvogadoGateway;

public class CadastrarAdvogadoUseCase {
    private final AdvogadoGateway advogadoGateway;

    public CadastrarAdvogadoUseCase(AdvogadoGateway advogadoGateway) {
        this.advogadoGateway = advogadoGateway;
    }

    public Advogado executar(Advogado advogado) {
        // Aqui poderiam entrar validações (ex: verificar CPF único, OAB válida, etc.)
        return advogadoGateway.salvar(advogado);
    }
}

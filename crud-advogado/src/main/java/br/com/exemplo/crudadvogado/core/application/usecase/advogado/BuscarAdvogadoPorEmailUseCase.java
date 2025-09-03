package br.com.exemplo.crudadvogado.core.application.usecase.advogado;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.AdvogadoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;
import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.mapper.AdvogadoMapper;
import org.springframework.stereotype.Service;

@Service
public class BuscarAdvogadoPorEmailUseCase {

    private final AdvogadoGateway advogadoGateway;

    public BuscarAdvogadoPorEmailUseCase(AdvogadoGateway advogadoGateway) {
        this.advogadoGateway = advogadoGateway;
    }

    public AdvogadoResponse executar(String email) {
        return advogadoGateway.buscarPorEmail(email)
                .map(AdvogadoMapper::toResponse)
                .orElseThrow(() -> new AdvogadoNaoEncontradoException("Advogado não encontrado com email: " + email));
    }

}

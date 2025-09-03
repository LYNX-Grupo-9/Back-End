package br.com.exemplo.crudadvogado.core.application.usecase.advogado;


import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.advogado.CriarAdvogadoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.CriarAdvogadoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.DuplicidadeException;
import br.com.exemplo.crudadvogado.core.domain.Advogado;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CriarAdvogadoUseCase {

    private final AdvogadoGateway advogadoGateway;
    private final PasswordEncoder passwordEncoder;

    public CriarAdvogadoUseCase(AdvogadoGateway advogadoGateway, PasswordEncoder passwordEncoder) {
        this.advogadoGateway = advogadoGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public CriarAdvogadoResponse excutar(CriarAdvogadoCommand command) {
        String senhaCriptografada = passwordEncoder.encode(command.senha());

        if(advogadoGateway.existePorEmail(command.email())) {
            throw new DuplicidadeException("Email já cadastrado");
        }
        if(advogadoGateway.existePorCpf(command.cpf())) {
            throw new DuplicidadeException("CPF já cadastrado");
        }
        if(advogadoGateway.existePorOab(command.oab())) {
            throw new DuplicidadeException("OAB já cadastrado");
        }

        Advogado advogadoParaRegistrar = Advogado.criarNovo(
                command.nome(),
                command.oab(),
                command.cpf(),
                command.email(),
                senhaCriptografada
        );

        Advogado advogadoCriado = advogadoGateway.criar(advogadoParaRegistrar);

        return new CriarAdvogadoResponse(
                advogadoCriado.getIdAdvogado(),
                advogadoCriado.getNome(),
                advogadoCriado.getEmail().getEnderacoEmail(),
                advogadoCriado.getCpf().getNumeroCpf(),
                advogadoCriado.getOab().getNumeroOab()
        );
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.advogado;

import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.AdvogadoDetalhes;
import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.repository.AdvogadoJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AutenticacaoUseCase implements UserDetailsService {

    private final AdvogadoJpaRepository repository;

    public AutenticacaoUseCase(AdvogadoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<AdvogadoEntity> advogadoOpt = repository.findByEmail(username);

        if (advogadoOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new AdvogadoDetalhes(advogadoOpt.get());
    }

}

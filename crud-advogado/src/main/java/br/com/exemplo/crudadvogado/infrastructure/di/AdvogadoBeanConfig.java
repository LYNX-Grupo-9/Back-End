package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.advogado.BuscarAdvogadoPorEmailUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.advogado.CriarAdvogadoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.AdvogadoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdvogadoBeanConfig {

    private final PasswordEncoder passwordEncoder;

    public AdvogadoBeanConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CriarAdvogadoUseCase criarAdvogadoUseCase(AdvogadoJpaAdapter adapter, PasswordEncoder passwordEncoder) {
        return new CriarAdvogadoUseCase(adapter, passwordEncoder);
    }

    @Bean
    public BuscarAdvogadoPorEmailUseCase buscarAdvogadoPorEmailUseCase(AdvogadoJpaAdapter adapter) {
        return new BuscarAdvogadoPorEmailUseCase(adapter);
    }


}

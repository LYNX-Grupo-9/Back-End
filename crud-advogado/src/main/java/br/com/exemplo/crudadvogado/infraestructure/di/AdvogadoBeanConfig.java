package br.com.exemplo.crudadvogado.infraestructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.advogado.CriarAdvogadoUseCase;
import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.adapter.AdvogadoJpaAdapter;
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

}

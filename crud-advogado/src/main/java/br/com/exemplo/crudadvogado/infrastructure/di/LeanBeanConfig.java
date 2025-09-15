package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.lead.EnviarLeadUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.LeadJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeanBeanConfig {

    @Bean
    public EnviarLeadUseCase enviarLeadUseCase(LeadJpaAdapter adapter) {
        return new EnviarLeadUseCase(adapter);
    }
}

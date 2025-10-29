package br.com.exemplo.crudadvogado.infrastructure.config;

import br.com.exemplo.crudadvogado.core.application.usecase.advogado.AutenticacaoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguracao {

    @Autowired
    private AutenticacaoUseCase autenticacaoService;

    @Autowired
    private AutenticacaoEntryPoint autenticacaoEntryPoint;

    private static final String[] URLS_PERMITIDAS = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/api/public/**",
            "/api/advogados/login",
            "/api/advogados/login/**",
            "/api/advogados/cadastrar",
            "/api/advogados/cadastrar/**",
            "/api/solicitacao-agendamento/solicitar",
            "/api/solicitacao-agendamento/solicitar/**",
            "/h2-console/**",
            "/error/**",
            "/v2/advogados/cadastrar",
            "/v2/advogados/cadastrar/**",
            "/v2/advogados/login",
            "/actuator/**",
            "/v3/api-docs/**",
            "/webjars/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Libera acesso ao H2, Swagger e frames
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                // Libera CORS globalmente (qualquer origem)
                .cors(Customizer.withDefaults())

                // Desativa CSRF (para APIs REST)
                .csrf(CsrfConfigurer::disable)

                // Configura autorizações
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(URLS_PERMITIDAS).permitAll()
                        .anyRequest().authenticated()
                )

                // Configura tratamento de exceção
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(autenticacaoEntryPoint)
                )

                // Define política de sessão stateless (para JWT)
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Adiciona o filtro JWT antes da autenticação padrão
        http.addFilterBefore(jwtAutenticacaoFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(new AutenticacaoProvider(autenticacaoService, passwordEncoder()));
        return builder.build();
    }

    @Bean
    public AutenticacaoEntryPoint jwtAuthenticationEntryPointBean() {
        return new AutenticacaoEntryPoint();
    }

    @Bean
    public AutenticacaoFilter jwtAutenticacaoFilterBean() {
        return new AutenticacaoFilter(autenticacaoService, jwtAuthenticationUtilBean());
    }

    @Bean
    public GerenciadorTokenJwt jwtAuthenticationUtilBean() {
        return new GerenciadorTokenJwt();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🌐 CORS global aberto (sem precisar definir IPs específicos)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuracao = new CorsConfiguration();
        configuracao.setAllowedOriginPatterns(List.of("*")); // aceita qualquer origem dinâmica
        configuracao.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()
        ));
        configuracao.setAllowedHeaders(List.of("*"));
        configuracao.setExposedHeaders(List.of(HttpHeaders.CONTENT_DISPOSITION));
        configuracao.setAllowCredentials(true); // permite cookies/token via proxy

        UrlBasedCorsConfigurationSource origem = new UrlBasedCorsConfigurationSource();
        origem.registerCorsConfiguration("/**", configuracao);

        return origem;
    }
}

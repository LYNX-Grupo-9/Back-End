package br.com.exemplo.crudadvogado.infraestructure.web.controller;


import br.com.exemplo.crudadvogado.core.application.dto.command.AdvogadoLoginCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.CriarAdvogadoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.AdvogadoToken;
import br.com.exemplo.crudadvogado.core.application.dto.response.CriarAdvogadoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.advogado.CriarAdvogadoUseCase;
import br.com.exemplo.crudadvogado.core.domain.Advogado;
import br.com.exemplo.crudadvogado.infraestructure.config.GerenciadorTokenJwt;
import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.mapper.AdvogadoMapper;
import br.com.exemplo.crudadvogado.infraestructure.persistence.jpa.repository.AdvogadoJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/advogados")
public class AdvogadoV2Controller {

    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final CriarAdvogadoUseCase criarAdvogadoUseCase;
    private final AuthenticationManager authenticationManager;
    private final AdvogadoJpaRepository advogadoJpaRepository;

    public AdvogadoV2Controller(GerenciadorTokenJwt gerenciadorTokenJwt, CriarAdvogadoUseCase criarAdvogadoUseCase, AuthenticationManager authenticationManager, AdvogadoJpaRepository advogadoJpaRepository) {
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.criarAdvogadoUseCase = criarAdvogadoUseCase;
        this.authenticationManager = authenticationManager;
        this.advogadoJpaRepository = advogadoJpaRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CriarAdvogadoResponse> criar(@RequestBody CriarAdvogadoCommand command) {
        CriarAdvogadoResponse advogadoCriado = criarAdvogadoUseCase.excutar(command);
        return ResponseEntity.status(201).body(advogadoCriado);
    }

    @PostMapping("/login")
    public ResponseEntity<AdvogadoToken> login(@RequestBody AdvogadoLoginCommand command) {
        final UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(command.email(), command.senha());

        final Authentication authentication = authenticationManager.authenticate(credentials);

        AdvogadoEntity advogadoAutenticado = advogadoJpaRepository
                .findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.gerarToken(authentication);

        AdvogadoToken response = AdvogadoMapper.of(advogadoAutenticado, token);

        return ResponseEntity.ok(response);
    }
}

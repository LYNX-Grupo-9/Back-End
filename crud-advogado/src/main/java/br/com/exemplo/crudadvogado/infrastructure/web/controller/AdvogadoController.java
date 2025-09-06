package br.com.exemplo.crudadvogado.infrastructure.web.controller;


import br.com.exemplo.crudadvogado.core.application.dto.command.advogado.AdvogadoLoginCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.advogado.CriarAdvogadoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.AdvogadoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.AdvogadoToken;
import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.CriarAdvogadoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.advogado.BuscarAdvogadoPorEmailUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.advogado.CriarAdvogadoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.config.GerenciadorTokenJwt;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.AdvogadoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.AdvogadoJpaRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advogados")
public class AdvogadoController {

    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final CriarAdvogadoUseCase criarAdvogadoUseCase;
    private final AuthenticationManager authenticationManager;
    private final AdvogadoJpaRepository advogadoJpaRepository;
    private final BuscarAdvogadoPorEmailUseCase buscarAdvogadoPorEmailUseCase;

    public AdvogadoController(GerenciadorTokenJwt gerenciadorTokenJwt, CriarAdvogadoUseCase criarAdvogadoUseCase, AuthenticationManager authenticationManager, AdvogadoJpaRepository advogadoJpaRepository, BuscarAdvogadoPorEmailUseCase buscarAdvogadoPorEmailUseCase) {
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.criarAdvogadoUseCase = criarAdvogadoUseCase;
        this.authenticationManager = authenticationManager;
        this.advogadoJpaRepository = advogadoJpaRepository;
        this.buscarAdvogadoPorEmailUseCase = buscarAdvogadoPorEmailUseCase;
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

    @GetMapping("/email/{email}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoResponse> buscarPorEmail(@PathVariable String email) {
        AdvogadoResponse response = buscarAdvogadoPorEmailUseCase.executar(email);
        return ResponseEntity.ok(response);
    }
}

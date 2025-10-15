package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento.AtualizarCategoriaCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento.CriarCategoriaEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.ContarCategoriasResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CriarCategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento.*;
import br.com.exemplo.crudadvogado.core.application.usecase.cliente.AtualizarClienteUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaEventoController {

    private final CriarCategoriaEventoUseCase criarCategoriaEventoUseCase;
    private final BuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase;
    private final BuscarCategoriasPorAdvogadoUseCase buscarCategoriasPorAdvogadoUseCase;
    private final BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase;
    private final DeletarCategoriaUseCase deletarCategoriaUseCase;
    private final AtualizarCategoriaParcialmenteUseCase atualizarCategoriaParcialmenteUseCase;
    private final ContarCategoriasPorNomeUseCase contarCategoriasPorNomeUseCase;

    public CategoriaEventoController(CriarCategoriaEventoUseCase criarCategoriaEventoUseCase, BuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase, BuscarCategoriasPorAdvogadoUseCase buscarCategoriasPorAdvogadoUseCase, BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase, DeletarCategoriaUseCase deletarCategoriaUseCase, AtualizarCategoriaParcialmenteUseCase atualizarCategoriaParcialmenteUseCase, ContarCategoriasPorNomeUseCase contarCategoriasPorNomeUseCase) {
        this.criarCategoriaEventoUseCase = criarCategoriaEventoUseCase;
        this.buscarTodasCategoriasUseCase = buscarTodasCategoriasUseCase;
        this.buscarCategoriasPorAdvogadoUseCase = buscarCategoriasPorAdvogadoUseCase;
        this.buscarCategoriaPorIdUseCase = buscarCategoriaPorIdUseCase;
        this.deletarCategoriaUseCase = deletarCategoriaUseCase;
        this.atualizarCategoriaParcialmenteUseCase = atualizarCategoriaParcialmenteUseCase;
        this.contarCategoriasPorNomeUseCase = contarCategoriasPorNomeUseCase;
    }


    @PostMapping("")
    @SecurityRequirement(name = "Bearer")
    public CriarCategoriaEventoResponse cadastrar(@RequestBody CriarCategoriaEventoCommand command) {
        return criarCategoriaEventoUseCase.executar(command);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CategoriaEventoResponse>> buscarTodasCategorias() {
        List<CategoriaEventoResponse> categorias = buscarTodasCategoriasUseCase.executar();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/advogado/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<CategoriaEventoResponse>> buscarCategoriasPorAdvogado(
            @PathVariable UUID idAdvogado) {
        List<CategoriaEventoResponse> categorias = buscarCategoriasPorAdvogadoUseCase.executar(idAdvogado);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{idCategoriaEvento}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CategoriaEventoResponse> buscarCategoriaPorId(
            @PathVariable Long idCategoriaEvento) {
        CategoriaEventoResponse categoria = buscarCategoriaPorIdUseCase.executar(idCategoriaEvento);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long idCategoria) {
        deletarCategoriaUseCase.executar(idCategoria);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CategoriaEventoResponse> atualizarCategoriaParcialmente(
            @PathVariable Long idCategoria,
            @Valid @RequestBody AtualizarCategoriaCommand command) {
        CategoriaEventoResponse response = atualizarCategoriaParcialmenteUseCase.executar(idCategoria, command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/contagem-por-nome/{idAdvogado}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ContarCategoriasResponse> contarCategoriasPorNome(
            @PathVariable UUID idAdvogado) {
        ContarCategoriasResponse response = contarCategoriasPorNomeUseCase.executar(idAdvogado);
        return ResponseEntity.ok(response);
    }
}

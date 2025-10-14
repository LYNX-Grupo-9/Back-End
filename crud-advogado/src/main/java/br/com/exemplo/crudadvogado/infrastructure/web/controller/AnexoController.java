package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.anexo.CriarAnexoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.anexo.AnexoResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.anexo.CriarAnexoResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.anexo.DeletarAnexoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.anexo.BuscarAnexoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.anexo.CriarAnexoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/anexos")
public class AnexoController {

    private final CriarAnexoUseCase  criarAnexoUseCase;
    private final BuscarAnexoUseCase buscarAnexoUseCase;
    private final DeletarAnexoUseCase deletarAnexoUseCase;

    public AnexoController(CriarAnexoUseCase criarAnexoUseCase, BuscarAnexoUseCase buscarAnexoUseCase, DeletarAnexoUseCase deletarAnexoUseCase) {
        this.criarAnexoUseCase = criarAnexoUseCase;
        this.buscarAnexoUseCase = buscarAnexoUseCase;
        this.deletarAnexoUseCase = deletarAnexoUseCase;
    }


    @PostMapping("")
    @SecurityRequirement(name = "Bearer")
    public CriarAnexoResponse criarAnexo(@RequestBody CriarAnexoCommand command){
        return criarAnexoUseCase.executar(command);
    }

    @GetMapping("/cliente/{idCliente}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AnexoResponse>> buscarPorCliente(@PathVariable UUID idCliente) {
        List<AnexoResponse> response = buscarAnexoUseCase.buscarPorIdCliente(idCliente);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/processo/{idProcesso}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AnexoResponse>> buscarPorProcesso(@PathVariable UUID idProcesso) {
        List<AnexoResponse> response = buscarAnexoUseCase.buscarPorIdProcesso(idProcesso);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarAnexo(@PathVariable String idItem) {
        deletarAnexoUseCase.executar(idItem);
        return ResponseEntity.noContent().build();
    }

}

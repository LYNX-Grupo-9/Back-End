package br.com.exemplo.crudadvogado.core.application.dto.response.processo;

import br.com.exemplo.crudadvogado.core.domain.Processo;

import java.util.UUID;

public record ProcessoResponse(
        UUID idProcesso,
        String titulo,
        String numeroProcesso,
        String descricao,
        String status,
        String classeProcessual,
        String assunto,
        String tribunal,
        String valor,
        String autor,
        String advRequerente,
        String reu,
        String advReu,
        UUID idAdvogado,
        UUID idCliente
) {
    public ProcessoResponse(Processo processo) {
        this(
                processo.getIdProcesso(),
                processo.getTitulo(),
                processo.getNumeroProcesso(),
                processo.getDescricao(),
                processo.getStatus(),
                processo.getClasseProcessual(),
                processo.getAssunto(),
                processo.getTribunal(),
                processo.getValor().toString(),
                processo.getAutor(),
                processo.getAdvRequerente(),
                processo.getReu(),
                processo.getAdvReu(),
                processo.getIdAdvogado(),
                processo.getIdCliente()
        );
    }
}

package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.evento.CriarEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.command.processo.CriarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.CriarProcessoResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;

public class CriarProcessoUseCase {

    private final ProcessoGateway processoGateway;

    public CriarProcessoUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    public CriarProcessoResponse executar(CriarProcessoCommand command) {
        Processo processoParaRegistrar = Processo.criarNovo(
                command.titulo(),
                command.numeroProcesso(),
                command.descricao(),
                command.status(),
                command.classeProcessual(),
                command.assunto(),
                command.tribunal(),
                command.valor(),
                command.autor(),
                command.advRequerente(),
                command.reu(),
                command.advReu(),
                command.idAdvogado(),
                command.idCliente()
        );

        Processo processoCriado = processoGateway.criar(processoParaRegistrar);

        return new CriarProcessoResponse(
                processoCriado.getTitulo(),
                processoCriado.getNumeroProcesso(),
                processoCriado.getDescricao(),
                processoCriado.getStatus(),
                processoCriado.getClasseProcessual(),
                processoCriado.getAssunto(),
                processoCriado.getTribunal(),
                processoCriado.getValor().toString(),
                processoCriado.getAutor(),
                processoCriado.getAdvRequerente(),
                processoCriado.getReu(),
                processoCriado.getAdvReu(),
                processoCriado.getIdAdvogado(),
                processoCriado.getIdCliente()
        );
    }

}

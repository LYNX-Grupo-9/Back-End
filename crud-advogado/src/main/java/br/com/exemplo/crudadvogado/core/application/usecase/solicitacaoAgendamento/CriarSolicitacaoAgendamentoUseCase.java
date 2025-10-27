package br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.solicitacaoAgendamento.CriarSolicitacaoAgendamentoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.message.SolicitacaoAgendamentoMessage;
import br.com.exemplo.crudadvogado.core.application.dto.response.solicitacaoAgendamento.CriarSolicitacaoAgendamentoResponse;
import br.com.exemplo.crudadvogado.core.domain.SolicitacaoAgendamento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.RabbitMQProducerService;

import java.time.LocalDate;

public class CriarSolicitacaoAgendamentoUseCase {

    private final SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway;

    private final RabbitMQProducerService rabbitMQProducerService;

    public CriarSolicitacaoAgendamentoUseCase(
            SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway,
            RabbitMQProducerService rabbitMQProducerService) {
        this.solicitacaoAgendamentoGateway = solicitacaoAgendamentoGateway;
        this.rabbitMQProducerService = rabbitMQProducerService;
    }

    public CriarSolicitacaoAgendamentoResponse executar(CriarSolicitacaoAgendamentoCommand command){
        SolicitacaoAgendamento solicitacaoParaRegistrar = SolicitacaoAgendamento.criarNovo(
                command.nome(),
                command.telefone(),
                command.email(),
                command.assunto(),
                command.dataSolicitacao(),
                command.horaSolicitacao(),
                command.idAdvogado()
        );

        SolicitacaoAgendamento solicitacaoCriada = solicitacaoAgendamentoGateway.criar(solicitacaoParaRegistrar);

        SolicitacaoAgendamentoMessage message = new SolicitacaoAgendamentoMessage(
                solicitacaoCriada.getIdSolicitacaoAgendamento(),
                solicitacaoCriada.getNome(),
                solicitacaoCriada.getTelefone(),
                solicitacaoCriada.getEmail(),
                solicitacaoCriada.getAssunto(),
                solicitacaoCriada.getDataSolicitacao(),
                solicitacaoCriada.getHoraSolicitacao(),
                solicitacaoCriada.getIdAdvogado(),
                "CRIADA",
                LocalDate.now()
        );

        rabbitMQProducerService.enviarSolicitacaoAgendamento(message);

        return new CriarSolicitacaoAgendamentoResponse(
                solicitacaoCriada.getIdSolicitacaoAgendamento(),
                solicitacaoCriada.getNome(),
                solicitacaoCriada.getTelefone(),
                solicitacaoCriada.getEmail(),
                solicitacaoCriada.getAssunto(),
                solicitacaoCriada.getDataSolicitacao(),
                solicitacaoCriada.getHoraSolicitacao(),
                solicitacaoCriada.getIdAdvogado()
        );
    }
}

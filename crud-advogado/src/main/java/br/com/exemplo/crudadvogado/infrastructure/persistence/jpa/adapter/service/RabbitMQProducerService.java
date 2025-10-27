package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service;

import br.com.exemplo.crudadvogado.core.application.dto.message.SolicitacaoAgendamentoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducerService.class);
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarSolicitacaoAgendamento(SolicitacaoAgendamentoMessage message) {
        try {
            rabbitTemplate.convertAndSend("agendamento.exchange", "solicitacao.agendamento.criada", message);
            logger.info("✅ Mensagem enviada para a fila - ID: {}", message.idSolicitacaoAgendamento());

        } catch (AmqpException e) {
            logger.warn("⚠️ RabbitMQ não disponível, mas a solicitação foi salva no banco. ID: {}",
                    message.idSolicitacaoAgendamento());
            logger.debug("Detalhes do erro RabbitMQ: {}", e.getMessage());
        }
    }
}

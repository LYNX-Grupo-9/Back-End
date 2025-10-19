package br.com.exemplo.crudadvogado.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "agendamento.exchange";
    public static final String QUEUE_SOLICITACOES = "solicitacao.agendamento.queue";
    public static final String ROUTING_KEY_SOLICITACAO = "solicitacao.agendamento.criada";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue solicitacaoQueue() {
        return new Queue(QUEUE_SOLICITACOES, true);
    }

    @Bean
    public Binding bindingSolicitacao(Queue solicitacaoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(solicitacaoQueue).to(exchange).with(ROUTING_KEY_SOLICITACAO);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}

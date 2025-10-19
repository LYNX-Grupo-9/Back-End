package br.com.exemplo.crudadvogado.infrastructure.consumer.service;

import br.com.exemplo.crudadvogado.core.application.dto.message.SolicitacaoAgendamentoMessage;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.AdvogadoJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolicitacaoAgendamentoConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SolicitacaoAgendamentoConsumer.class);
    private final EmailService emailService;
    private final AdvogadoJpaRepository advogadoJpaRepository;

    public SolicitacaoAgendamentoConsumer(EmailService emailService,
                                          AdvogadoJpaRepository advogadoJpaRepository) {
        this.emailService = emailService;
        this.advogadoJpaRepository = advogadoJpaRepository;
    }

    @RabbitListener(queues = "solicitacao.agendamento.queue")
    public void receberSolicitacaoAgendamento(SolicitacaoAgendamentoMessage message) {
        try {
            logger.info("🎯 CONSUMIDOR - MENSAGEM RECEBIDA! ID: {}", message.idSolicitacaoAgendamento());
            logger.debug("📦 Conteúdo completo da mensagem: {}", message);

            String dataFormatada = "Data não informada";
            if (message.dataSolicitacao() != null) {
                LocalDate dataLocalDate = message.dataSolicitacao()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                dataFormatada = dataLocalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } else {
                logger.warn("⚠️ Data da solicitação veio nula para a mensagem ID {}", message.idSolicitacaoAgendamento());
            }

            String horaFormatada = "Horário não informado";
            LocalTime hora = message.horaSolicitacao();

            if (hora != null) {
                horaFormatada = hora.format(DateTimeFormatter.ofPattern("HH:mm"));
            } else {
                logger.warn("⚠️ Hora da solicitação veio nula para a mensagem ID {}", message.idSolicitacaoAgendamento());
            }

            String nomeAdvogado = buscarNomeAdvogado(message.idAdvogado());

            emailService.enviarEmailConfirmacaoAgendamento(
                    message.email(),
                    message.nome(),
                    message.assunto(),
                    dataFormatada,
                    horaFormatada,
                    nomeAdvogado
            );

            logger.info("📧 EMAIL ENVIADO com sucesso para: {}", message.email(), nomeAdvogado);
            logger.info("✅ CONSUMIDOR - Solicitação processada! ID: {}", message.idSolicitacaoAgendamento());

        } catch (Exception e) {
            logger.error("❌ CONSUMIDOR - Erro ao processar mensagem ID {}: {}",
                    message.idSolicitacaoAgendamento(), e.getMessage(), e);
            throw new RuntimeException("Falha no processamento", e);
        }
    }

    private String buscarNomeAdvogado(UUID idAdvogado) {
        if (idAdvogado == null) {
            logger.warn("⚠️ ID do advogado não informado na mensagem.");
            return "Advogado não identificado";
        }

        try {
            Optional<AdvogadoEntity> advogadoOpt = advogadoJpaRepository.findById(idAdvogado);

            if (advogadoOpt.isPresent()) {
                AdvogadoEntity advogado = advogadoOpt.get();
                logger.info("✅ Advogado encontrado: {} (ID: {})", advogado.getNome(), idAdvogado);
                return advogado.getNome();
            } else {
                logger.warn("⚠️ Advogado não encontrado no banco. ID: {}", idAdvogado);
                return "Advogado não encontrado";
            }

        } catch (Exception e) {
            logger.error("❌ Erro ao buscar advogado no banco. ID: {} - Erro: {}", idAdvogado, e.getMessage());
            return "Erro ao buscar advogado";
        }
    }
}

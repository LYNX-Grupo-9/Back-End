package br.com.exemplo.crudadvogado.infrastructure.consumer.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.Locale;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void enviarEmailConfirmacaoAgendamento(
            String destinatario,
            String nomeCliente,
            String assuntoConsulta,
            String dataConsulta,
            String horaConsulta,
            String nomeAdvogado) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject("Confirmação de Solicitação de Agendamento");
            helper.setFrom("nao-responder@advogados.com.br");

            // Prepara o contexto para o template
            Context context = new Context(new Locale("pt", "BR"));
            context.setVariable("nomeCliente", nomeCliente);
            context.setVariable("assuntoConsulta", assuntoConsulta);
            context.setVariable("dataConsulta", dataConsulta);
            context.setVariable("horaConsulta", horaConsulta);
            context.setVariable("nomeAdvogado", nomeAdvogado);

            // Processa o template HTML
            String htmlContent = templateEngine.process("confirmacao-agendamento", context);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar email de confirmação", e);
        }
    }

    // Método alternativo para email simples (texto puro)
    public void enviarEmailSimplesConfirmacao(
            String destinatario,
            String nomeCliente,
            String assuntoConsulta,
            String dataConsulta,
            String horaConsulta) {

        String textoEmail = String.format(
                "Olá %s,\n\n" +
                        "Sua solicitação de agendamento foi recebida com sucesso!\n\n" +
                        "Detalhes do agendamento:\n" +
                        "Assunto: %s\n" +
                        "Data: %s\n" +
                        "Hora: %s\n\n" +
                        "Nossa equipe entrará em contato em breve para confirmar o agendamento.\n\n" +
                        "Atenciosamente,\nEquipe de Agendamentos",
                nomeCliente, assuntoConsulta, dataConsulta, horaConsulta
        );

        // Para email simples, você pode usar SimpleMailMessage
        // SimpleMailMessage message = new SimpleMailMessage();
        // message.setTo(destinatario);
        // message.setSubject("Confirmação de Solicitação de Agendamento");
        // message.setText(textoEmail);
        // mailSender.send(message);
    }
}

package com.healthturing.healthturing_server.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    /**
     * Envía email con formato html a un destinatario, cond header y footer por defecto.
     * @param to
     * @param subject 
     * @param htmlContent 
     * @throws MessagingException
     */
    @Async
    public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {

        if (to == null || to.isEmpty() || subject == null || subject.isEmpty() || htmlContent == null || htmlContent.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros del email no pueden ser nulos o vacíos");
        }

        String header = "<div style='padding: 10px; text-align: center; border-radius: 4px; background-color: #f8f8f8;'>"
        + "<h1><span style='color:#2DABB9;'>Health</span><span style='color:#81CDD5;'>Turing</span></h1>"
        + "</div>";

        String footer = "<div style='padding: 10px; text-align: center; border-radius: 5px; background-color: #f8f8f8;'><p style='font-size: 12px; color: #666;'>"
        + "Este correo electrónico ha sido enviado desde <a href='https://healthturing.duckdns.org' style='text-decoration: none; color: #337ab7;'>healthturing.duckdns.org</a>.<br/>"
        + "Si tienes alguna pregunta o inquietud, no dudes en hacérnoslo saber.<br>¡Gracias por confiar en nosotros!</p>" + "<h2><span style='color:#2DABB9;'>Health</span><span style='color:#81CDD5;'>Turing</span></h2></div>";

        String htmlContentWithFooter = header + htmlContent + footer;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContentWithFooter, true);
        
        javaMailSender.send(message);
        System.out.println("Correo HTML enviado con éxito a: " + to);
    }
}
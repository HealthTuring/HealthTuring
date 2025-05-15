package com.healthturing.healthturing_server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * Servicio encargado de enviar emails
 * Emplea JavaMailSender
 */
@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * Envía un email a un correo destino
     * @param to email destinatario
     * @param subject Asunto
     * @param text Texto
     */
    @Async
    public void sendEmail(String to, String subject, String text) {
        if (to == null || to.isEmpty() || subject == null || subject.isEmpty() || text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros del email no pueden ser nulos o vacíos");
        }
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        
        javaMailSender.send(message);
        System.out.println("El correo ha sido enviado con éxito a: " + to);
    }



    /**
     * Envía email con formato html a un destinatario
     * @param to email destinatario
     * @param subject Asunto
     * @param htmlContent Contenido HTML
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
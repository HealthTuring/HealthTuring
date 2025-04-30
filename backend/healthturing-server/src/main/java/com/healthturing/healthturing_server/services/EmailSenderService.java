package com.healthturing.healthturing_server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        if (to == null || to.isEmpty() || subject == null || subject.isEmpty() || htmlContent == null || htmlContent.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros del email no pueden ser nulos o vacíos");
        }

        String footer = "<p style='margin-top: 20px; font-size: 12px; color: #666; text-align: center; padding: 10px; border-top: 1px solid #ccc;'>"
        + "Este correo electrónico ha sido enviado desde <a href='https://healthturing.duckdns.org' style=''>healthturing.duckdns.org</a>.<br/>"
        + "Si tienes alguna pregunta o inquietud, no dudes en hacérnoslo saber.<br>¡Gracias por confiar en nosotros!</p>" + "<h2 style=''>Next</span></h2>";

        String htmlContentWithFooter = htmlContent + footer;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContentWithFooter, true);
        
        javaMailSender.send(message);
        System.out.println("Correo HTML enviado con éxito a: " + to);
    }
}
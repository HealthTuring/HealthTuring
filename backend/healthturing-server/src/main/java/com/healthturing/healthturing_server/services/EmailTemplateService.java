package com.healthturing.healthturing_server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.healthturing.healthturing_server.exceptions.EmailSendingException;

import jakarta.mail.MessagingException;

@Service
public class EmailTemplateService {

    @Value("${url.client}")
    private String clientUrl;

    @Autowired
    private EmailSenderService emailSenderService;

    public void sendResetPasswordEmail(String email, String token) {
        String confirmationLink = clientUrl + "/auth/reset-password/" + token;
        String htmlContent = String.format(
            """
                <html>
                    <head>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                margin: 0;
                                padding: 0;
                            }
                            .container {
                                max-width: 600px;
                                margin: 10px auto;
                                line-height: 1.6;
                                color: #333333;
                            }
                            .container h2 {
                                color: #2DABB9;
                            }
                            .button-container {
                                text-align: center;
                                margin: 20px 0;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                                <h2>Restablecer tu contraseña</h2>
                                <p>Hola,</p>
                                <p>Recibimos una solicitud para restablecer tu contraseña en HealthTuring. Si no realizaste esta solicitud, puedes ignorar este correo.</p>
                                <p>Haz clic en el botón de abajo para restablecer tu contraseña:</p>
                                <div class="button-container">
                                    <a href="%s" style="background-color: #2DABB9; color: #ffffff; text-decoration: none; padding: 10px 20px; border-radius: 5px; font-size: 16px; display: inline-block;">Restablecer Contraseña</a>
                                </div>
                                <p>Este enlace es válido por 30 minutos.</p>
                        </div>
                    </body>
                </html>
            """,
                confirmationLink);

        try {
            this.emailSenderService.sendHtmlEmail(email, "Restablecimiento de contraseña", htmlContent);
        } catch (MessagingException e) {
            throw new EmailSendingException("Error al enviar el correo de restablecimiento de contraseña", e);
        }
    }

    public void sendConfirmationEmail(String email, String token) {
        String confirmationLink = clientUrl + "/auth/email-confirmation/" + token;
        String htmlContent = String.format(
            """
                <html>
                    <head>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                margin: 0;
                                padding: 0;
                            }
                            .container {
                                max-width: 600px;
                                margin: 10px auto;
                                line-height: 1.6;
                                color: #333333;
                            }
                            .container h2 {
                                color: #2DABB9;
                            }
                            .button-container {
                                text-align: center;
                                margin: 20px 0;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                                <h2>Confirmación de Registro</h2>
                                <p>Hola,</p>
                                <p>Gracias por registrarte en nuestra plataforma. Estamos encantados de que te hayas unido a nosotros.</p>
                                <p>Para completar tu registro, por favor haz clic en el siguiente botón:</p>
                                <div class="button-container">
                                    <a href="%s" style="background-color: #2DABB9; color: #ffffff; text-decoration: none; padding: 10px 20px; border-radius: 5px; font-size: 16px; display: inline-block;">Confirmar Registro</a>
                                </div>
                                <p>Este enlace es válido por 3 días.</p>
                        </div>
                    </body>
                </html>
            """,
                confirmationLink);

        try {
            this.emailSenderService.sendHtmlEmail(email, "Confirmación de registro", htmlContent);
        } catch (MessagingException e) {
            throw new EmailSendingException("Error al enviar el correo de confirmación de registro", e);
        }
    }

}

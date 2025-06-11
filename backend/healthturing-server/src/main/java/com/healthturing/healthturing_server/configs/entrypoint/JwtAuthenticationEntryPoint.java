package com.healthturing.healthturing_server.configs.entrypoint;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /* @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/html");
            response.getWriter().write(
                "<!DOCTYPE html>" +
                "<html>" +
                "<head><title>No autorizado</title></head>" +
                "<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>" +
                "<h1>401 - No autorizado</h1>" +
                "<p>No tienes permisos para acceder a este recurso.</p>" +
                "</body>" +
                "</html>"
            );
    } */

        @Override
        public void commence(HttpServletRequest request,
                            HttpServletResponse response,
                            AuthenticationException authException) throws IOException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }



/* @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             String acceptHeader = request.getHeader("Accept");

        if (acceptHeader != null && acceptHeader.contains("application/json")) {
            response.setContentType("application/json");
            response.getWriter().write("{\"Error\": \"No autorizado\"}");
        } else {
            response.setContentType("text/html");
            response.getWriter().write(
                "<!DOCTYPE html>" +
                "<html>" +
                "<head><title>No autorizado</title></head>" +
                "<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>" +
                "<h1>401 - No autorizado</h1>" +
                "<p>No tienes permisos para acceder a este recurso.</p>" +
                "</body>" +
                "</html>"
            );
        }
    } */
}
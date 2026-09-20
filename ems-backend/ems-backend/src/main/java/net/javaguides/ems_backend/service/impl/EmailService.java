package net.javaguides.ems_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    /**
     * Sends a welcome email using a dynamic HTML template.
     * * @param toEmail The recipient's email address.
     * @param name The employee's name (for personalization).
     */
    // *** METHOD SIGNATURE MODIFIED: Removed the 'username' argument ***
    public void sendWelcomeEmail(String toEmail, String name) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // --- Email Details ---
            helper.setTo(toEmail);
            helper.setSubject("Welcome to MUHALI HOLDINGS!");
            helper.setFrom("arenahomuhali@gmail.com");

            // 2. Prepare the Thymeleaf Context (Data Model)
            Context context = new Context();
            context.setVariable("name", name);
            // Since username is removed, we can pass the email address as the username
            // to satisfy the template's need for a login ID.
            context.setVariable("username", toEmail);
            context.setVariable("loginUrl", "http://localhost:8080/login");

            // 3. Process the HTML Template
            String htmlContent = templateEngine.process("welcome-email", context);

            // 4. Set the email content as HTML
            helper.setText(htmlContent, true);

            // 5. Send the email
            mailSender.send(message);

            System.out.println("Welcome email sent successfully to " + toEmail);

        } catch (MessagingException e) {
            System.err.println("Error sending HTML welcome email: " + e.getMessage());
            throw new RuntimeException("Failed to send welcome email.", e);
        }
    }
}
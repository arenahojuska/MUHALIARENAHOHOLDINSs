package API_Testing;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class EmailEngine {

    /**
     * Dispatches the generated Word document to the configured recipient email.
     * @param attachmentFilePath The absolute system file path to the .docx report.
     */
    public static void sendReport(String attachmentFilePath) {
        System.out.println(">>> Initializing independent email engine routing...");

        // 1. Account Configuration Details
        final String senderEmail = "johnsmith4235671234@gmail.com"; 
        final String appPassword = "cxhwuwjdlrwasudw"; 
        final String recipientEmail = "areenahojuska@gmail.com"; 

        // 2. Define standard SMTP session configurations
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // Mandate secure TLS transmission

        // 3. Establish an authenticated connection session profile
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

        try {
            // 4. Instantiate and build the email envelope structure
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("MUHALI HOLDINGS - AUTOMATED TEST SUITE EXECUTED");

            // 5. Build the text body block
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Greetings,\n\nPlease find attached the updated automated test status execution report for Muhali Holdings.\n\nBest Regards,\nAutomation Execution Engine");

            // 6. Build the file attachment block
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            File reportFile = new File(attachmentFilePath);
            
            if (reportFile.exists()) {
                attachmentBodyPart.attachFile(reportFile);
            } else {
                System.err.println("CRITICAL EMAIL ENGINE FAILURE: File attachment not found at path: " + attachmentFilePath);
                return;
            }

            // 7. Combine both text message and file attachment together
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Bind the full container payload to the message body
            message.setContent(multipart);

            // 8. Fire transmission out over the internet
            Transport.send(message);
            System.out.println(">>> SUCCESS: Email dispatched cleanly to: " + recipientEmail);

        } catch (Exception e) {
            System.err.println(">>> SEVERE: External Email Engine execution dropped due to a structural transmission failure:");
            e.printStackTrace();
        }
    }
}
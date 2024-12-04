package lk.ijse.crop_monitoring_systembackend.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lk.ijse.crop_monitoring_systembackend.customObj.MailBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class EmailUtil {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private String loadHtmlTemplate(String templateName, Map<String, String> replacements) throws IOException {
        String templatePath = "src/main/resources/templates/" + templateName + ".html";
        String content = new String(Files.readAllBytes(Paths.get(templatePath)));
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            String placeholder = "\\{" + entry.getKey() + "\\}";
            content = content.replaceAll(placeholder, entry.getValue());
        }
        return content;
    }

    public void sendHtmlMessage(MailBody mailBody) throws MessagingException, IOException {
        String htmlContent = loadHtmlTemplate(mailBody.templateName(), mailBody.replacements());
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(message, true);
        helper.setTo(mailBody.to());
        helper.setFrom(fromEmail);
        helper.setSubject(mailBody.subject());
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }

    public Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}

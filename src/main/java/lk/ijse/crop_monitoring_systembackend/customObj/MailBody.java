package lk.ijse.crop_monitoring_systembackend.customObj;

import lombok.Builder;

import java.util.Map;

@Builder
public record MailBody(String to, String subject, String templateName, Map<String, String> replacements) {}
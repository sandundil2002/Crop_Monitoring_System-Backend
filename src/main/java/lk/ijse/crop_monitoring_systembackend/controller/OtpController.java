package lk.ijse.crop_monitoring_systembackend.controller;

import lk.ijse.crop_monitoring_systembackend.customObj.MailBody;
import lk.ijse.crop_monitoring_systembackend.customObj.OTPResponse;
import lk.ijse.crop_monitoring_systembackend.util.EmailUtil;
import lk.ijse.crop_monitoring_systembackend.util.OtpManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/otp")
@RequiredArgsConstructor
public class OtpController {
    private final OtpManager otpManager;
    private final EmailUtil emailUtil;

    @PostMapping("/send")
    public ResponseEntity<OTPResponse> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Generate OTP
        Integer otp = emailUtil.otpGenerator();

        // Store OTP
        otpManager.storeOtp(email, otp.toString());

        // Send OTP via email
        try {
            MailBody mailBody = MailBody.builder()
                    .to(email)
                    .subject("Your OTP Code")
                    .templateName("otpTemplate")
                    .replacements(Map.of("otp", otp.toString()))
                    .build();

            emailUtil.sendHtmlMessage(mailBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Return OTP response (avoid exposing the OTP in production)
        OTPResponse response = new OTPResponse(otp.toString(), email);
        return ResponseEntity.ok(response);
    }
}

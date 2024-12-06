package lk.ijse.crop_monitoring_systembackend.controller;

import lk.ijse.crop_monitoring_systembackend.customObj.MailBody;
import lk.ijse.crop_monitoring_systembackend.customObj.OTPResponse;
import lk.ijse.crop_monitoring_systembackend.dto.UserDTO;
import lk.ijse.crop_monitoring_systembackend.exception.DataPersistFailedException;
import lk.ijse.crop_monitoring_systembackend.jwtModel.JWTAuthResponse;
import lk.ijse.crop_monitoring_systembackend.jwtModel.SignIn;
import lk.ijse.crop_monitoring_systembackend.service.AuthenticationService;
import lk.ijse.crop_monitoring_systembackend.service.JWTService;
import lk.ijse.crop_monitoring_systembackend.service.UserService;
import lk.ijse.crop_monitoring_systembackend.util.EmailUtil;
import lk.ijse.crop_monitoring_systembackend.util.OtpManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final OtpManager otpManager;
    private final EmailUtil emailUtil;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody UserDTO user) {
        if (user != null) {
            try {
                boolean exist = userService.searchUser(user.getEmail());
                if (exist) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userService.saveUser(user);
                    logger.info("User saved successfully: " + user);
                    return ResponseEntity.ok(authenticationService.signUp(user));
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (DataPersistFailedException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.severe("Failed to save user: " + user);
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn sign) {
        logger.info("User signed in successfully: " + sign);
        return ResponseEntity.ok(authenticationService.signIn(sign));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            boolean isValid = !jwtService.isTokenExpired(token);
            if (isValid) {
                return ResponseEntity.ok("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken (@RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }

    @PostMapping("/send_otp")
    public ResponseEntity<OTPResponse> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Integer otp = emailUtil.otpGenerator();
        otpManager.storeOtp(email, otp.toString());

        try {
            MailBody mailBody = MailBody.builder()
                    .to(email)
                    .subject("Your OTP Code")
                    .templateName("otpTemplate")
                    .replacements(Map.of("otp", otp.toString()))
                    .build();

            emailUtil.sendHtmlMessage(mailBody);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        OTPResponse response = new OTPResponse(otp.toString(), email);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate_otp")
    public ResponseEntity<OTPResponse> validateOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        if (email == null || otp == null || email.isEmpty() || otp.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        boolean isValid = otpManager.validateOtp(email, otp);
        if (isValid) {
            return ResponseEntity.ok(new OTPResponse(otp, email));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
package lk.ijse.crop_monitoring_systembackend.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OTPResponse {
    private String otp;
    private String username;
}
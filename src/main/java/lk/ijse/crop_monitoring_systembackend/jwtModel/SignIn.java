package lk.ijse.crop_monitoring_systembackend.jwtModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignIn {
    public String email;
    public String password;
}

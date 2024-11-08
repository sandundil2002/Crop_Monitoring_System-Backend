package lk.ijse.crop_monitoring_systembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    public String email;
    public String password;
    public Role role;

    public enum Role {
        MANAGER, ADMINISTRATIVE, SCIENTIST, OTHER
    }
}

package lk.ijse.crop_monitoring_systembackend.dto;

import lk.ijse.crop_monitoring_systembackend.customResponse.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements Serializable, Response {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private LocalDate joinedDate;
    private LocalDate dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String mobile;
    private String email;
    private Role role;
    private List<VehicleDTO> vehicles;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum Role {
        MANAGER, ADMINISTRATIVE, SCIENTIST, OTHER
    }
}
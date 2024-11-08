package lk.ijse.crop_monitoring_systembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements Serializable {
    private String vehicleId;
    private String category;
    private String numberPlate;
    private String fuelType;
    private String status;
    private String remarks;
}

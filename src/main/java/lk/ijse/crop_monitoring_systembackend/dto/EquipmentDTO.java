package lk.ijse.crop_monitoring_systembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements Serializable {
    private String equipmentId;
    private String category;
    private String type;
    private String status;
    private List<StaffDTO> staff;
    private List<FieldDTO> fields;
}

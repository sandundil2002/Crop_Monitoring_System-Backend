package lk.ijse.crop_monitoring_systembackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Category cannot be blank")
    @Size(max = 50, message = "Category must be at most 50 characters")
    private String category;
    @NotBlank(message = "Type cannot be blank")
    @Size(max = 50, message = "Type must be at most 50 characters")
    private String type;
    @NotBlank(message = "Status cannot be blank")
    @Size(max = 50, message = "Status must be at most 50 characters")
    private String status;
    private List<@NotNull(message = "Staff list contains null elements") StaffDTO> staff;
    private List<@NotNull(message = "Fields list contains null elements") FieldDTO> fields;
}

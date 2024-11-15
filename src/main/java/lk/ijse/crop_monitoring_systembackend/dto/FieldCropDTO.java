package lk.ijse.crop_monitoring_systembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldCropDTO implements Serializable {
    private String fieldCropId;
    private String fieldId;
    private List<String> cropId;
    private LocalDate assignedDate;
}
